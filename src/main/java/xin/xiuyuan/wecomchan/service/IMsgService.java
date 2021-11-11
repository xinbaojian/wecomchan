package xin.xiuyuan.wecomchan.service;

import xin.xiuyuan.wecomchan.model.MsgResult;

/**
 * @author xinbj
 */
public interface IMsgService {

    /**
     * 获取access_token
     *
     * @return access_token
     */
    String getAccessToken();

    /**
     * 发送文本消息
     *
     * @param content 文本消息
     * @return 发送结果
     */
    MsgResult sendText(String content);

    /**
     * 发送卡片消息
     *
     * @param title       消息标题
     * @param description 消息描述
     * @param url         消息URl
     * @param btnTxt      按钮文本
     * @return 发送结果
     */
    MsgResult sendTextCard(String title, String description, String url, String btnTxt);

    /**
     * 发送卡片消息
     *
     * @param title       消息标题
     * @param description 消息描述
     * @param url         消息URl
     * @return 发送结果
     */
    MsgResult sendTextCard(String title, String description, String url);
}
