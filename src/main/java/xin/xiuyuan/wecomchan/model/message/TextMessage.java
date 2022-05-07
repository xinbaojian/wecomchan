package xin.xiuyuan.wecomchan.model.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 微信xml消息对象
 *
 * @author xinbaojian
 * @program wecomchan
 * @create 2022-05-07 16:24
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TextMessage extends Message {

    private String agentId;

}
