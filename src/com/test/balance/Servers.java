package com.test.balance;

import java.util.HashMap;

/**
 * @author zhouj
 * @since 2020-08-14
 */
public class Servers {

    public HashMap<String, Server> serverMap = new HashMap() {
        {
            put("192.168.1.1", new Server(5,5,"192.168.1.1"));
            put("192.168.1.2", new Server(1,1,"192.168.1.2"));
            put("192.168.1.3", new Server(1,1,"192.168.1.3"));
        }
    };
}
