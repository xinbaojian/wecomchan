package controllers

import (
	"encoding/json"
	"fmt"
	beego "github.com/beego/beego/v2/server/web"
	"wecomchan/models"
)

// WeComInfoController operations for WeComInfo
type WeComInfoController struct {
	beego.Controller
}

//Get @Title Get 获取配置信息
//@Description 查看配置信息
//@router /:aid [get]
func (o *WeComInfoController) Get() {
	aid := o.Ctx.Input.Param(":aid")
	record, err := models.GetWeComInfoByAid(aid)
	if err != nil {
		o.Data["json"] = err.Error()
	} else {
		o.Data["json"] = record
	}
	o.ServeJSON()
}

// GetAll @Title Get 查询所有应用配置信息
//@Description 查询所有应用配置信息
//@router /list [get]
func (o *WeComInfoController) GetAll() {
	records, err := models.GetAll()
	fmt.Println(records, err)
	if err != nil {
		o.Data["json"] = err.Error()
	} else {
		o.Data["json"] = records
	}
	o.ServeJSON()
}

// Post @Title Get 新增应用配置信息
//@Description 新增应用配置信息
//@router / [post]
func (o *WeComInfoController) Post() {
	var record models.WeComInfo
	err := json.Unmarshal(o.Ctx.Input.RequestBody, &record)
	fmt.Println("参数信息：", record)
	_, err = models.SaveWeComInfo(&record)
	if err != nil {
		o.Data["json"] = err.Error()
	} else {
		o.Data["json"] = record.Id
	}
	o.ServeJSON()
}
