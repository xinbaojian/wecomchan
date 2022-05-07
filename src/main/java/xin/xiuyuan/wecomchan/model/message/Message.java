package xin.xiuyuan.wecomchan.model.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 微信xml消息基类
 *
 * @author xinbaojian
 * @program wecomchan
 * @create 2022-05-07 16:25
 **/
@Data
@Accessors(chain = true)
public class Message {

    private String toUserName;
    private String fromUserName;
    private String createTime;
    private String msgType;
    private String content;
    private String msgId;

}
