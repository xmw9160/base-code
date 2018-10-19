package com.xmw.lombok;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Date 2018/1/29.
 * Author xmw .
 */
@Data
@NoArgsConstructor
public class LombokEntity {
    private Integer age;
    private String name;
    private Date birthday;
    private String address;
}
