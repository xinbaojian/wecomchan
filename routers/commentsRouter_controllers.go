package routers

import (
	"github.com/astaxie/beego"
	"github.com/astaxie/beego/context/param"
)

func init() {

	beego.GlobalControllerRouter["wecomchan/controllers:MessageController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:MessageController"],
		beego.ControllerComments{
			Method:           "Get",
			Router:           "/:message",
			AllowHTTPMethods: []string{"get"},
			MethodParams:     param.Make(),
			Filters:          nil,
			Params:           nil})

	beego.GlobalControllerRouter["wecomchan/controllers:MessageController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:MessageController"],
		beego.ControllerComments{
			Method:           "PostNews",
			Router:           "/news",
			AllowHTTPMethods: []string{"post"},
			MethodParams:     param.Make(),
			Filters:          nil,
			Params:           nil})

	beego.GlobalControllerRouter["wecomchan/controllers:MessageController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:MessageController"],
		beego.ControllerComments{
			Method:           "PostTextCard",
			Router:           "/text/card",
			AllowHTTPMethods: []string{"post"},
			MethodParams:     param.Make(),
			Filters:          nil,
			Params:           nil})

}
