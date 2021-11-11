package xin.xiuyuan.wecomchan.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xinbj
 * @date 2021-11-11 9:23
 */
@Data
@Accessors(chain = true)
public class MsgResult {

    /**
     * 返回码
     */
    private Integer errcode;

    /**
     * 返回消息
     */
    private String errmsg;

    /**
     * 消息ID
     */
    private String msgid;


}
