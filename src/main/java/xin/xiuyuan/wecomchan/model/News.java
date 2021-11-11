package xin.xiuyuan.wecomchan.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xinbj
 * @date 2021-11-11 11:51
 */
@Data
@Accessors(chain = true)
public class News {

    /**
     * 图文消息，一个图文消息支持1到8条图文
     */
    private List<Article> articles;
}
