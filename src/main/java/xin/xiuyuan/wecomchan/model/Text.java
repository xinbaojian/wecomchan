package xin.xiuyuan.wecomchan.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xinbj
 * @date 2021-11-11 9:15
 */
@Data
@Accessors(chain = true)
public class Text {

    /**
     * 消息内容
     */
    private String content;
}
