package xin.xiuyuan.wecomchan.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xinbj
 * @date 2021-11-11 11:41
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MarkdownMsg extends Msg {

    /**
     * 消息类型，此时固定为：markdown
     */
    private String msgtype = "markdown";

    /**
     * markdown 消息内容
     */
    private Markdown markdown;
}
