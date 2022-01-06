package controllers

import (
	"encoding/json"
	beego "github.com/beego/beego/v2/server/web"
	"net/url"
	"wecomchan/models"
	"wecomchan/service"
)

// MessageController Operations about Messages
type MessageController struct {
	beego.Controller
}

//Get @Title Get 发送文本消息
//@Description 发送纯文本消息，文本支持超链接
//@Param aid path string  true 应用ID
//@Param message	path string true "要发送得文本消息"
//@router /:aid/:message [get]
func (o *MessageController) Get() {
	aid := o.Ctx.Input.Param(":aid")
	message := o.Ctx.Input.Param(":message")
	decodedValue, err := url.QueryUnescape(message)
	result, err := service.SendTextMessage(aid, decodedValue)
	if err != nil {
		o.Data["json"] = err.Error()
	} else {
		o.Data["json"] = result
	}
	o.ServeJSON()
}

// PostTextCard @Title Post 发送文本卡片消息
//@Description 发送文本卡片消息
//@Param aid path string  true 应用ID
//@Param body models.TextCard true "文本卡片消息体"
//@router /:aid/text/card [post]
func (o *MessageController) PostTextCard() {
	aid := o.Ctx.Input.Param(":aid")
	var textCard models.TextCard
	err := json.Unmarshal(o.Ctx.Input.RequestBody, &textCard)
	if err != nil {
		o.Data["json"] = err.Error()
	} else {
		message, err := service.SendTextCardMessage(aid, &textCard)
		if err != nil {
			o.Data["json"] = err.Error()
		} else {
			o.Data["json"] = message
		}
	}
	o.ServeJSON()
}

// PostNews @Title Get 测试
//@Description 发送图文消息，最多8个
//@Param aid path string  true 应用ID
//@Param 	body 	[]models.WecomArticle	true "图文消息json对象"
//@router /:aid/news [post]
func (o *MessageController) PostNews() {
	aid := o.Ctx.Input.Param(":aid")
	var articles []models.WecomArticle
	err := json.Unmarshal(o.Ctx.Input.RequestBody, &articles)
	if err != nil {
		o.Data["json"] = err.Error()
	} else {
		message, err := service.SendNewsMessage(aid, &articles)
		if err != nil {
			o.Data["json"] = err.Error()
		} else {
			o.Data["json"] = message
		}
	}
	o.ServeJSON()
}
