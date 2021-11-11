package xin.xiuyuan.wecomchan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.xiuyuan.wecomchan.service.IMsgService;

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

    @GetMapping("/text/card")
    public HttpEntity<?> pushTextCardMsg(@RequestParam(value = "title") String title,
                                         @RequestParam(value = "description") String description,
                                         @RequestParam(value = "url") String url,
                                         @RequestParam(value = "btnTxt", required = false, defaultValue = "详情") String btnTxt) {
        return ResponseEntity.ok(msgService.sendTextCard(title, description, url, btnTxt));
    }
}
