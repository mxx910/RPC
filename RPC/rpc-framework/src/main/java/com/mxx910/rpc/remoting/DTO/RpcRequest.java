package com.mxx910.rpc.remoting.DTO;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author: mxx910
 * @date: 2023/6/26
 * @description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;
    public String getRpcServiceName() {
        return this.getInterfaceName() + this.getGroup() + this.getVersion();
    }

}
