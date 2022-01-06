package service

import (
	"context"
	"encoding/json"
	"fmt"
	"github.com/beego/beego/v2/client/cache"
	"github.com/beego/beego/v2/core/logs"
	"github.com/go-resty/resty/v2"
	"os"
	"strconv"
	"sync"
	"time"
	"wecomchan/models"
)

// GetAccessTokenUrl 获取 access_token 链接
var GetAccessTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s"

var sendMsgUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s"

var bm cache.Cache

var mutex sync.Mutex

func init() {
	bm, _ = cache.NewCache("memory", `{"interval":60}`)
	logs.Info(bm)
}

// GetEnvDefault 获取配置信息，未获取到则取默认值
func GetEnvDefault(key, defVal string) string {
	val, ex := os.LookupEnv(key)
	if !ex {
		return defVal
	}
	return val
}

// GetAccessToken 获取AccessToken
func GetAccessToken(aid string) (string, error) {
	mutex.Lock()
	defer mutex.Unlock()
	key, err := bm.Get(context.TODO(), aid)
	if err == nil {
		logs.Info("从缓存中获取Key")
		return StrVal(key), err
	} else {
		logs.Error(err)
	}
	//查询配置信息
	weComInfo, err := models.GetWeComInfoByAid(aid)
	if err != nil {
		logs.Error("aid 账号不存在", err)
		return "", err
	}
	client := resty.New()
	url := fmt.Sprintf(GetAccessTokenUrl, weComInfo.CorpId, weComInfo.WecomSecret)
	responseBody, err := client.R().Get(url)
	if err != nil {
		logs.Error("获取AccessToken出错了", err)
		time.Sleep(60 * time.Second)
		return GetAccessToken(aid)
	}
	var msgResult models.MsgResult
	json.Unmarshal(responseBody.Body(), &msgResult)
	if msgResult.ErrCode == 0 {
		logs.Info("把key 放入缓存中..")
		bm.Put(context.TODO(), aid, msgResult.AccessToken, 7200*time.Second)
	}
	return msgResult.AccessToken, err
}

// StrVal 获取变量的字符串值
// 浮点型 3.0将会转换成字符串3, "3"
// 非数值或字符类型的变量将会被转换成JSON格式字符串
func StrVal(value interface{}) string {
	var key string
	if value == nil {
		return key
	}

	switch value.(type) {
	case float64:
		ft := value.(float64)
		key = strconv.FormatFloat(ft, 'f', -1, 64)
	case float32:
		ft := value.(float32)
		key = strconv.FormatFloat(float64(ft), 'f', -1, 64)
	case int:
		it := value.(int)
		key = strconv.Itoa(it)
	case uint:
		it := value.(uint)
		key = strconv.Itoa(int(it))
	case int8:
		it := value.(int8)
		key = strconv.Itoa(int(it))
	case uint8:
		it := value.(uint8)
		key = strconv.Itoa(int(it))
	case int16:
		it := value.(int16)
		key = strconv.Itoa(int(it))
	case uint16:
		it := value.(uint16)
		key = strconv.Itoa(int(it))
	case int32:
		it := value.(int32)
		key = strconv.Itoa(int(it))
	case uint32:
		it := value.(uint32)
		key = strconv.Itoa(int(it))
	case int64:
		it := value.(int64)
		key = strconv.FormatInt(it, 10)
	case uint64:
		it := value.(uint64)
		key = strconv.FormatUint(it, 10)
	case string:
		key = value.(string)
	case []byte:
		key = string(value.([]byte))
	default:
		newValue, _ := json.Marshal(value)
		key = string(newValue)
	}

	return key
}

func SendNewsMessage(aid string, articles *[]models.WecomArticle) (models.MsgResult, error) {
	newsMsg := models.NewsMsg{
		Msg: models.Msg{
			ToUser:  "@all",
			AgentId: aid,
			MsgType: "news",
		},
		News: models.News{
			Articles: articles,
		},
	}
	str, _ := json.Marshal(newsMsg)
	return PostMessage(aid, string(str))
}

// SendTextMessage 发送纯文本消息
func SendTextMessage(aid, message string) (models.MsgResult, error) {
	textMsg := models.TextMsg{
		Msg: models.Msg{
			ToUser:  "@all",
			AgentId: aid,
			MsgType: "text",
		},
		Text: models.Text{
			Content: message,
		},
	}
	str, _ := json.Marshal(textMsg)
	return PostMessage(aid, string(str))
}

// SendTextCardMessage 发送文本卡片消息
func SendTextCardMessage(aid string, textCard *models.TextCard) (models.MsgResult, error) {
	textCardMsg := models.TextCardMsg{
		Msg: models.Msg{
			ToUser:  "@all",
			AgentId: aid,
			MsgType: "textcard",
		},
		TextCard: textCard,
	}
	str, _ := json.Marshal(textCardMsg)
	return PostMessage(aid, string(str))
}

// PostMessage 推送消息
func PostMessage(aid, body string) (models.MsgResult, error) {
	var msgResult = models.MsgResult{}
	client := resty.New()
	accessToken, err := GetAccessToken(aid)
	if err != nil {
		return msgResult, err
	}
	url := fmt.Sprintf(sendMsgUrl, accessToken)
	responseBody, err := client.R().
		SetBody(body).
		Post(url)
	if err != nil {
		logs.Error("发送消息出错了", err)
		return msgResult, err
	}
	err = json.Unmarshal(responseBody.Body(), &msgResult)
	if err != nil {
		return models.MsgResult{}, err
	}
	//access_token 失效错误码
	invalidAccessTokenCode := 42001
	if msgResult.ErrCode == invalidAccessTokenCode {
		//access_token 已失效 清除access_token 尝试重试一次
		err = bm.ClearAll(context.TODO())
		if err != nil {
			return models.MsgResult{}, err
		}
		responseBody, err = client.R().
			SetBody(body).
			Post(url)
		err = json.Unmarshal(responseBody.Body(), &msgResult)
		if err != nil {
			return models.MsgResult{}, err
		}
	}
	return msgResult, nil
}
