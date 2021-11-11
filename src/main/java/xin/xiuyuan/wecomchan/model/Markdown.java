package xin.xiuyuan.wecomchan.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xinbj
 * @date 2021-11-11 11:42
 */
@Data
@Accessors(chain = true)
public class Markdown {

    /**
     * markdown 内容
     */
    private String content;
}
