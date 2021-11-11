package xin.xiuyuan.wecomchan.model;

import lombok.Data;

/**
 * @author xinbj
 * @date 2021-11-11 8:54
 */
@Data
public class Msg {

    /**
     * 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
     */
    private String touser = "@all";

    /**
     * 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
     */
    private String toparty;

    /**
     * 标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
     */
    private String totag;

    /**
     * 企业应用的id，整型。企业内部开发，可在应用的设置页面查看；
     */
    private String agentid;

}
