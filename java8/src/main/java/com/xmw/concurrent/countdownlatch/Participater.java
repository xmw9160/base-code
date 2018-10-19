package com.xmw.concurrent.countdownlatch;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/3/9 13:06
 * @since V1.0
 */
public class Participater implements Runnable {
    private String name;
    private Conference conference;

    public Participater(String name, Conference conference) {
        this.name = name;
        this.conference = conference;
    }

    public void run() {
        conference.arrive(name);
    }
}
