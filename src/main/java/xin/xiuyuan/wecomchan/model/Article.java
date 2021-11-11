package xin.xiuyuan.wecomchan.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xinbj
 * @date 2021-11-11 11:51
 */
@Data
@Accessors(chain = true)
public class Article {

    /**
     * 标题，不超过128个字节，超过会自动截断
     */
    private String title;

    /**
     * 描述，不超过512个字节，超过会自动截断
     */
    private String description;

    /**
     * 点击后跳转的链接。 最长2048字节，请确保包含了协议头(http/https)，小程序或者url必须填写一个
     */
    private String url;

    /**
     * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图 1068*455，小图150*150。
     */
    private String picurl;

}
