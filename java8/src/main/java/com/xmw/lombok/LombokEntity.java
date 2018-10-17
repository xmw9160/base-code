package com.xmw.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
