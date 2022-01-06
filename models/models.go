package models

import (
	"github.com/beego/beego/v2/client/orm"
	"github.com/beego/beego/v2/core/logs"
	"github.com/beego/beego/v2/server/web"
	_ "github.com/mattn/go-sqlite3"
)

func init() {
	// need to register db driver
	err := orm.RegisterDriver("sqlite", orm.DRSqlite)
	if err != nil {
		logs.Error(err)
	}
	db, _ := web.AppConfig.String("database")
	// need to register default database
	err = orm.RegisterDataBase("default", "sqlite3", db)
	if err != nil {
		logs.Error(err)
	}
	// 需要在init中注册定义的model
	orm.RegisterModel(new(WeComInfo))
	//自动建表
	// 如果是开发模式， 则显示命令信息
	err = orm.RunSyncdb("default", false, true)
	if err != nil {
		logs.Informational("[orm] Create table err : ", err)
	}
	//关闭开启打印SQL
	//orm.Debug = true
}
