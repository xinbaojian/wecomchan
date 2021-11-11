package xin.xiuyuan.wecomchan.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xinbj
 * @date 2021-11-11 11:50
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class NewsMsg extends Msg {

    /**
     * 消息类型，此时固定为：news
     */
    private String msgtype = "news";

    /**
     * 图文消息
     */
    private News news;
}
