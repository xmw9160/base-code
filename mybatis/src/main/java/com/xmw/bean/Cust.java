package com.xmw.bean;

import lombok.Data;

@Data
public class Cust {
    private String id;

    private String custname;

    /**
     * 证件号码
     */
    private String certNo;
}
