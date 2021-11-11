package xin.xiuyuan.wecomchan.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xinbj
 * @date 2021-11-11 9:37
 */
@Data
@Accessors(chain = true)
public class TextCard {

    /**
     * 标题，不超过128个字节，超过会自动截断（支持id转译）
     */
    private String title;

    /**
     * 描述，不超过512个字节，超过会自动截断（支持id转译）
     */
    private String description;

    /**
     * 点击后跳转的链接。最长2048字节，请确保包含了协议头(http/https)
     */
    private String url;

    /**
     * 按钮文字。 默认为“详情”， 不超过4个文字，超过自动截断。
     */
    private String btntxt = "详情";

}
