package xin.xiuyuan.wecomchan.service;

import xin.xiuyuan.wecomchan.model.Article;
import xin.xiuyuan.wecomchan.model.MsgResult;

import java.util.List;

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

    /**
     * 发送markdown 消息
     *
     * @param markdown markdown 内容
     * @return 发送结果
     */
    MsgResult sendMarkdown(String markdown);

    /**
     * 发送图文消息
     *
     * @param articles 图文列表
     * @return 发送结果
     */
    MsgResult sendNews(List<Article> articles);
}
