package com.mxx910.rpc;

import lombok.*;

import java.io.Serializable;

/**
 * @author: mxx910
 * @date: 2023/6/28
 * @description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Hello implements Serializable {
    private String message;
    private String description;
}
