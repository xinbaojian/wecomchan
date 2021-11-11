package xin.xiuyuan.wecomchan.constant;

/**
 * 企业微信相关链接
 *
 * @author xinbj
 * @date 2021-11-10 17:01
 */
public interface UrlConstant {

    /**
     * 获取 access_token 链接
     */
    String GET_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={}&corpsecret={}";

    /**
     * 发送消息接口
     */
    String SEND_MSG_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={}";
}
