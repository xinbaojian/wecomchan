package controllers

import (
	"encoding/json"
	"fmt"
	"github.com/astaxie/beego"
	"wecomchan/models"
	"wecomchan/service"
)

// MessageController Operations about Messages
type MessageController struct {
	beego.Controller
}

//Get @Title Get 发送文本消息
//@Description 发送纯文本消息，文本支持超链接
//@Param message	path string true "要发送得文本消息"
//@router /:message [get]
func (o *MessageController) Get() {
	message := o.Ctx.Input.Param(":message")
	fmt.Println(service.SendTextMessage(message))
	o.ServeJSON()
}

// PostTextCard @Title Post 发送文本卡片消息
//@Description 发送文本卡片消息
//@Param body body models.TextCard true "文本卡片消息体"
//@router /text/card [post]
func (o *MessageController) PostTextCard() {
	var textCard models.TextCard
	err := json.Unmarshal(o.Ctx.Input.RequestBody, &textCard)
	if err != nil {
		o.Data["json"] = err.Error()
	} else {
		message, err := service.SendTextCardMessage(&textCard)
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
//@Param 	body 	body 	[]models.WecomArticle	true "图文消息json对象"
//@router /news [post]
func (o *MessageController) PostNews() {
	var articles []models.WecomArticle
	err := json.Unmarshal(o.Ctx.Input.RequestBody, &articles)
	if err != nil {
		o.Data["json"] = err.Error()
	} else {
		message, err := service.SendNewsMessage(&articles)
		if err != nil {
			o.Data["json"] = err.Error()
		} else {
			o.Data["json"] = message
		}
	}
	o.ServeJSON()
}
