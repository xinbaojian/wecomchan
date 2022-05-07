package xin.xiuyuan.wecomchan;

import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import xin.xiuyuan.wecomchan.model.message.TextMessage;

import javax.xml.xpath.XPathConstants;

/**
 * 微信消息辅助类
 *
 * @author xinbaojian
 * @program wecomchan
 * @create 2022-05-07 16:23
 **/

public class XmlMessageUtils {

    /**
     * 解析文本消息
     *
     * @param xmlBody 文本消息xml格式
     * @return {@link TextMessage}
     */
    public static TextMessage parseTextMessage(String xmlBody) {
        Document doc = XmlUtil.readXML(xmlBody);
        TextMessage textMessage = new TextMessage();
        textMessage.setAgentId(XmlUtil.getByXPath("//AgentID", doc, XPathConstants.STRING).toString())
                .setToUserName(XmlUtil.getByXPath("//ToUserName", doc, XPathConstants.STRING).toString())
                .setFromUserName(XmlUtil.getByXPath("//FromUserName", doc, XPathConstants.STRING).toString())
                .setCreateTime(XmlUtil.getByXPath("//CreateTime", doc, XPathConstants.STRING).toString())
                .setMsgType(XmlUtil.getByXPath("//MsgType", doc, XPathConstants.STRING).toString())
                .setContent(XmlUtil.getByXPath("//Content", doc, XPathConstants.STRING).toString())
                .setMsgId(XmlUtil.getByXPath("//MsgId", doc, XPathConstants.STRING).toString());
        return textMessage;
    }
}
