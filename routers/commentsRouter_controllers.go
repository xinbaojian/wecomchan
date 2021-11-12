package routers

import (
	"github.com/astaxie/beego"
	"github.com/astaxie/beego/context/param"
)

func init() {

    beego.GlobalControllerRouter["wecomchan/controllers:MessageController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:MessageController"],
        beego.ControllerComments{
            Method: "Get",
            Router: `/:message`,
            AllowHTTPMethods: []string{"get"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["wecomchan/controllers:MessageController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:MessageController"],
        beego.ControllerComments{
            Method: "PostNews",
            Router: `/news`,
            AllowHTTPMethods: []string{"post"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["wecomchan/controllers:MessageController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:MessageController"],
        beego.ControllerComments{
            Method: "PostTextCard",
            Router: `/textcard`,
            AllowHTTPMethods: []string{"post"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["wecomchan/controllers:ObjectController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:ObjectController"],
        beego.ControllerComments{
            Method: "Post",
            Router: `/`,
            AllowHTTPMethods: []string{"post"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["wecomchan/controllers:ObjectController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:ObjectController"],
        beego.ControllerComments{
            Method: "GetAll",
            Router: `/`,
            AllowHTTPMethods: []string{"get"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["wecomchan/controllers:ObjectController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:ObjectController"],
        beego.ControllerComments{
            Method: "Get",
            Router: `/:objectId`,
            AllowHTTPMethods: []string{"get"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["wecomchan/controllers:ObjectController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:ObjectController"],
        beego.ControllerComments{
            Method: "Put",
            Router: `/:objectId`,
            AllowHTTPMethods: []string{"put"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["wecomchan/controllers:ObjectController"] = append(beego.GlobalControllerRouter["wecomchan/controllers:ObjectController"],
        beego.ControllerComments{
            Method: "Delete",
            Router: `/:objectId`,
            AllowHTTPMethods: []string{"delete"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

}
