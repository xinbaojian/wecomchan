package xin.xiuyuan.wecomchan.service;

import xin.xiuyuan.wecomchan.model.Article;
import xin.xiuyuan.wecomchan.model.MsgResult;
import xin.xiuyuan.wecomchan.model.TextCard;

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
     * @param textCard 文本卡片消息
     * @return 发送结果
     */
    MsgResult sendTextCard(TextCard textCard);

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
