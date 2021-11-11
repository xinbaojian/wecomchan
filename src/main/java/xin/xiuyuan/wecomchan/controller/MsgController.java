package xin.xiuyuan.wecomchan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xin.xiuyuan.wecomchan.model.Article;
import xin.xiuyuan.wecomchan.model.TextCard;
import xin.xiuyuan.wecomchan.service.IMsgService;

import java.util.List;

/**
 * @author xinbj
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/msg")
public class MsgController {

    private final IMsgService msgService;

    /**
     * 发送文本消息
     *
     * @param msg 文本消息
     */
    @GetMapping("/text")
    public HttpEntity<?> pushTextMsg(@RequestParam(value = "msg") String msg) {
        return ResponseEntity.ok(msgService.sendText(msg));
    }

    /**
     * 发送文本卡片消息
     *
     * @param textCard 卡片消息对象
     */
    @PostMapping("/text/card")
    public HttpEntity<?> pushTextCardMsg(@RequestBody TextCard textCard) {
        return ResponseEntity.ok(msgService.sendTextCard(textCard));
    }

    /**
     * 发送markdown 消息
     *
     * @param markdown markdown 内容
     */
    @GetMapping("/markdown")
    public HttpEntity<?> pushMarkdown(@RequestParam(value = "markdown") String markdown) {
        return ResponseEntity.ok(msgService.sendMarkdown(markdown));
    }

    /**
     * 发送图文消息
     *
     * @param articles 图文消息对象
     */
    @PostMapping("/news")
    public HttpEntity<?> pushMarkdown(@RequestBody List<Article> articles) {
        return ResponseEntity.ok(msgService.sendNews(articles));
    }
}
