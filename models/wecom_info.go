package models

import (
	"github.com/beego/beego/v2/client/orm"
)

// WeComInfo 企业微信 应用配置
type WeComInfo struct {
	//id
	Id int
	//企业微信公司ID
	CorpId string
	//企业微信应用Secret
	WecomSecret string
	//企业微信应用ID
	WecomAid string
}

// SaveWeComInfo 保存
func SaveWeComInfo(record *WeComInfo) (id int64, err error) {
	o := orm.NewOrm()
	return o.Insert(record)
}

// GetWeComInfoByAid 根据aid 查询配置信息
func GetWeComInfoByAid(aid string) (record WeComInfo, err error) {
	o := orm.NewOrm()
	err = o.QueryTable("WeComInfo").Filter("WecomAid", aid).One(&record)
	return record, err
}

func GetAll() (records []*WeComInfo, err error) {
	o := orm.NewOrm()
	_, err = o.QueryTable("WeComInfo").All(&records)
	return records, err
}
