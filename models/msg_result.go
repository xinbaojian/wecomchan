package models

type MsgResult struct {
	//错误码
	ErrCode int `json:"errcode"`
	//错误信息
	ErrMsg string `json:"errmsg"`
	//消息ID
	MsgId string `json:"msgid"`
	//授权码
	AccessToken string `json:"access_token"`
	//有效期 单位秒
	ExpiresIn int `json:"expires_in"`
}
