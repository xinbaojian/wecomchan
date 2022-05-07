package xin.xiuyuan.wecomchan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.xiuyuan.wecomchan.XmlMessageUtils;
import xin.xiuyuan.wecomchan.aes.WxBizMsgCrypt;
import xin.xiuyuan.wecomchan.exception.AesException;
import xin.xiuyuan.wecomchan.model.message.TextMessage;
import xin.xiuyuan.wecomchan.service.IMsgService;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author xinbaojian
 * @program wecomchan
 * @create 2022-05-07 15:33
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/wecom")
public class WeChatController {

    /**
     * Token
     */
    @Value("${wecom.token}")
    private String token;

    /**
     * EncodingAESKey
     */
    @Value("${wecom.encodingAesKey}")
    private String encodingAesKey;

    /**
     * 企业ID
     */
    @Value("${wecom.corpid}")
    private String corpId;

    private final IMsgService msgService;

    /**
     * 微信公众号接入
     *
     * @param msg_signature 企业微信加密签名，msg_signature结合了企业填写的token、请求中的timestamp、nonce参数、加密的消息体
     * @param timestamp     时间戳
     * @param nonce         随机数
     * @param echostr       加密的字符串。需要解密得到消息内容明文，解密后有random、msg_len、msg、CorpID四个字段，其中msg即为消息内容明文
     */
    @GetMapping("/verify")
    public HttpEntity<?> verify(String msg_signature, String timestamp, String nonce, String echostr) {
        try {
            WxBizMsgCrypt wxcpt = new WxBizMsgCrypt(token, encodingAesKey, corpId);
            String result = wxcpt.verifyUrl(msg_signature, timestamp, nonce, echostr);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (AesException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 接收微信公众号消息
     *
     * @param msg_signature 企业微信加密签名，msg_signature结合了企业填写的token、请求中的timestamp、nonce参数、加密的消息体
     * @param timestamp     时间戳
     * @param nonce         随机数
     */
    @PostMapping("/verify")
    public HttpEntity<?> message(String msg_signature, String timestamp, String nonce, HttpServletRequest request) throws IOException, AesException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String requestVal = "msg_signature:" + msg_signature + "; timestamp:" + timestamp + ";nonce " + nonce;
        System.out.println(requestVal);
        String lines;
        StringBuilder bodyXml = new StringBuilder();
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
            bodyXml.append(lines);
        }
        WxBizMsgCrypt msgCrypt = new WxBizMsgCrypt(token, encodingAesKey, corpId);
        String content = msgCrypt.decryptMsg(msg_signature, timestamp, nonce, bodyXml.toString());
        TextMessage textMessage = XmlMessageUtils.parseTextMessage(content);
        return new ResponseEntity<>(msgService.sendText(textMessage.getFromUserName(), textMessage.getContent()), HttpStatus.OK);
    }
}
