package xin.xiuyuan.wecomchan.service.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xin.xiuyuan.wecomchan.constant.GlobalConstant;
import xin.xiuyuan.wecomchan.constant.UrlConstant;
import xin.xiuyuan.wecomchan.model.*;
import xin.xiuyuan.wecomchan.service.IMsgService;

import java.util.List;

/**
 * @author xinbj
 * @date 2021-11-10 16:49
 */
@Slf4j
@Service
public class MsgServiceImpl implements IMsgService {

    /**
     * 企业ID
     */
    @Value("${wecom.corpid}")
    private String corpId;

    /**
     * 应用的凭证密钥
     */
    @Value("${wecom.corpsecret}")
    private String corpSecret;

    /**
     * 应用ID
     */
    @Value("${wecom.aid}")
    private String wecomAid;

    /**
     * 缓存对象
     */
    private static final TimedCache<String, String> TIME_CACHE = CacheUtil.newTimedCache(7200 * 1000);

    static {
        TIME_CACHE.schedulePrune(7200 * 1000);
    }


    @Override
    public String getAccessToken() {
        String accessToken = TIME_CACHE.get(GlobalConstant.ACCESS_TOKEN_KEY);
        if (StrUtil.isBlank(accessToken)) {
            String responseBody = HttpUtil.get(StrUtil.format(UrlConstant.GET_ACCESS_TOKEN_URL, corpId, corpSecret));
            JSONObject jsonObject = JSONUtil.parseObj(responseBody);
            accessToken = jsonObject.getStr("access_token");
            long expiresIn = jsonObject.getLong("expires_in");
            TIME_CACHE.put(GlobalConstant.ACCESS_TOKEN_KEY, accessToken, expiresIn);
        }
        return accessToken;
    }

    @Override
    public MsgResult sendText(String content) {
        TextMsg textMsg = new TextMsg();
        textMsg.setAgentid(wecomAid);
        textMsg.setText(new Text().setContent(content));
        String body = JSONUtil.toJsonStr(textMsg);
        return post(body);
    }

    @Override
    public MsgResult sendTextCard(TextCard textCard) {
        TextCartMsg textCartMsg = new TextCartMsg();
        textCartMsg.setAgentid(wecomAid);
        if (StrUtil.isBlank(textCard.getBtntxt())) {
            textCard.setBtntxt("详情");
        }
        textCartMsg.setTextcard(textCard);
        String body = JSONUtil.toJsonStr(textCartMsg);
        return post(body);
    }


    @Override
    public MsgResult sendMarkdown(String content) {
        MarkdownMsg markdownMsg = new MarkdownMsg();
        markdownMsg.setAgentid(wecomAid);
        Markdown markdown = new Markdown();
        markdown.setContent(content);
        markdownMsg.setMarkdown(markdown);
        String body = JSONUtil.toJsonStr(markdownMsg);
        return post(body);
    }

    @Override
    public MsgResult sendNews(List<Article> articles) {
        NewsMsg newsMsg = new NewsMsg();
        newsMsg.setAgentid(wecomAid);
        News news = new News();
        newsMsg.setNews(news);
        news.setArticles(articles);
        String body = JSONUtil.toJsonStr(newsMsg);
        return post(body);
    }

    private MsgResult post(String body) {
        String responseBody = HttpUtil.post(StrUtil.format(UrlConstant.SEND_MSG_URL, getAccessToken()), body);
        MsgResult msgResult = JSONUtil.toBean(responseBody, MsgResult.class);
        //access_token 失效错误码
        int invalidAccessTokenCode = 42001;
        if (msgResult.getErrcode() == invalidAccessTokenCode) {
            //access_token 已失效 清除access_token 尝试重试一次
            TIME_CACHE.clear();
            log.error("errorCode: {}, errorMsg: {}", msgResult.getErrcode(), msgResult.getErrmsg());
            responseBody = HttpUtil.post(StrUtil.format(UrlConstant.SEND_MSG_URL, getAccessToken()), body);
            msgResult = JSONUtil.toBean(responseBody, MsgResult.class);
        } else if (msgResult.getErrcode() != 0) {
            log.error("errorCode: {}, errorMsg: {}", msgResult.getErrcode(), msgResult.getErrmsg());
        }
        return msgResult;
    }
}
