package routers

import (
	beego "github.com/beego/beego/v2/server/web"
	"github.com/beego/beego/v2/server/web/context/param"
)

func init() {

	beego.GlobalControllerRouter["wecomchan/controllers:MessageController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:MessageController"],
		beego.ControllerComments{
			Method:           "Get",
			Router:           "/:aid/:message",
			AllowHTTPMethods: []string{"get"},
			MethodParams:     param.Make(),
			Filters:          nil,
			Params:           nil})

	beego.GlobalControllerRouter["wecomchan/controllers:MessageController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:MessageController"],
		beego.ControllerComments{
			Method:           "PostNews",
			Router:           "/:aid/news",
			AllowHTTPMethods: []string{"post"},
			MethodParams:     param.Make(),
			Filters:          nil,
			Params:           nil})

	beego.GlobalControllerRouter["wecomchan/controllers:MessageController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:MessageController"],
		beego.ControllerComments{
			Method:           "PostTextCard",
			Router:           "/:aid/text/card",
			AllowHTTPMethods: []string{"post"},
			MethodParams:     param.Make(),
			Filters:          nil,
			Params:           nil})

	beego.GlobalControllerRouter["wecomchan/controllers:WeComInfoController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:WeComInfoController"],
		beego.ControllerComments{
			Method:           "Post",
			Router:           "/",
			AllowHTTPMethods: []string{"post"},
			MethodParams:     param.Make(),
			Filters:          nil,
			Params:           nil})

	beego.GlobalControllerRouter["wecomchan/controllers:WeComInfoController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:WeComInfoController"],
		beego.ControllerComments{
			Method:           "Get",
			Router:           "/:aid",
			AllowHTTPMethods: []string{"get"},
			MethodParams:     param.Make(),
			Filters:          nil,
			Params:           nil})

	beego.GlobalControllerRouter["wecomchan/controllers:WeComInfoController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:WeComInfoController"],
		beego.ControllerComments{
			Method:           "GetAll",
			Router:           "/list",
			AllowHTTPMethods: []string{"get"},
			MethodParams:     param.Make(),
			Filters:          nil,
			Params:           nil})

}
