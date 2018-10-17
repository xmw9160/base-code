package com.xmw.limitflow;

/**
 * Date 2018/2/3.
 * Author xmw .
 */
@SuppressWarnings("Duplicates")
public class TokenBucketDemo {
    /*
     * 注意到，漏桶的出水速度是恒定的，那么意味着如果瞬时大流量的话，
     * 将有大部分请求被丢弃掉（也就是所谓的溢出）。为了解决这个问题，令牌桶进行了算法改进。
     */
    /*
     * 生成令牌的速度是恒定的，而请求去拿令牌是没有速度限制的。
     * 这意味，面对瞬时大流量，该算法可以在短时间内请求拿到大量令牌，
     * 而且拿令牌的过程并不是消耗很大的事情。（有一点生产令牌，消费令牌的意味）.
     * 不论是对于令牌桶拿不到令牌被拒绝，还是漏桶的水满了溢出，都是为了保证大部分
     * 流量的正常使用，而牺牲掉了少部分流量，这是合理的，如果因为极少部分
     * 流量需要保证的话，那么就可能导致系统达到极限而挂掉，得不偿失。
     */

    private static long time = System.currentTimeMillis();
    private static int createTokenRate = 3;
    private static int size = 10;
    // 当前令牌数
    private static int tokens = 0;

    public static boolean grant() {
        long now = System.currentTimeMillis();
        // 在这段时间内需要产生的令牌数量
        int in = (int) ((now - time) / 50 * createTokenRate);
        tokens = Math.min(size, tokens + in);
        time = now;
        if (tokens > 0) {
            --tokens;
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (grant()) {
                        System.out.println("执行业务逻辑");
                    } else {
                        System.out.println("限流");
                    }
                }
            }).start();
        }
    }
}
