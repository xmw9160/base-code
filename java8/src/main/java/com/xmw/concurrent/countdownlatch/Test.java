package com.xmw.concurrent.countdownlatch;

/**
 * TODO 功能描述
 *
 * @author mingwei.xia
 * @date 2018/3/9 13:07
 * @since V1.0
 */
public class Test {
    public static void main(String[] args) {
        //启动会议室线程，等待与会人员参加会议
        Conference conference = new Conference(3);
        new Thread(conference).start();

        for (int i = 0; i < 3; i++) {
            Participater participater = new Participater("chenssy-0" + i, conference);
            Thread thread = new Thread(participater);
            thread.start();
        }
    }
}
