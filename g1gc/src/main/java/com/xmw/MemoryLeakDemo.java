package com.xmw;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xmw.
 * @date 2018/6/24 23:36.
 */
public class MemoryLeakDemo {

    public static void main(String[] args) {

        Map<Key, String> map = new HashMap<>();
        while (true) {
            for (int i = 0; i < 10000; i++) {
//                if (!map.containsKey(new Key(i))) {
                map.put(new Key(i), "number:" + i);
//                }
            }
        }
    }

    static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }
}
