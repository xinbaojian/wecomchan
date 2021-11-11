package xin.xiuyuan.wecomchan.service.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xin.xiuyuan.wecomchan.constant.GlobalConstant;
import xin.xiuyuan.wecomchan.constant.UrlConstant;
import xin.xiuyuan.wecomchan.model.*;
import xin.xiuyuan.wecomchan.service.IMsgService;

/**
 * @author xinbj
 * @date 2021-11-10 16:49
 */
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
        String responseBody = HttpUtil.post(StrUtil.format(UrlConstant.SEND_MSG_URL, getAccessToken()), body);
        return JSONUtil.toBean(responseBody, MsgResult.class);
    }

    @Override
    public MsgResult sendTextCard(String title, String description, String url, String btnTxt) {
        TextCartMsg textCartMsg = new TextCartMsg();
        textCartMsg.setAgentid(wecomAid);
        TextCard textCard = new TextCard();
        textCard.setTitle(title).setDescription(description).setUrl(url);
        if (StrUtil.isNotBlank(btnTxt)) {
            textCard.setBtntxt(btnTxt);
        }
        textCartMsg.setTextcard(textCard);
        String body = JSONUtil.toJsonStr(textCartMsg);
        String responseBody = HttpUtil.post(StrUtil.format(UrlConstant.SEND_MSG_URL, getAccessToken()), body);
        return JSONUtil.toBean(responseBody, MsgResult.class);
    }

    @Override
    public MsgResult sendTextCard(String title, String description, String url) {
        return sendTextCard(title, description, url, null);
    }
}
