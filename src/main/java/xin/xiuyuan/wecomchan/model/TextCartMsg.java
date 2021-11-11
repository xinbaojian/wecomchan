package xin.xiuyuan.wecomchan.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xinbj
 * @date 2021-11-11 9:35
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TextCartMsg extends Msg {

    /**
     * 消息类型，此时固定为：text
     */
    private String msgtype = "textcard";

    /**
     * 文本卡片消息
     */
    private TextCard textcard;
}
