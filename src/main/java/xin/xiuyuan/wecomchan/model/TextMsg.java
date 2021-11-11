package xin.xiuyuan.wecomchan.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xinbj
 * @date 2021-11-11 9:04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TextMsg extends Msg {

    /**
     * 消息类型，此时固定为：text
     */
    private String msgtype = "text";

    /**
     * 消息内容对象
     */
    private Text text;
}
