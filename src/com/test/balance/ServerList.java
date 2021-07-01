package com.test.balance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouj
 * @since 2020-08-14
 */
public class ServerList {

    public List<String> list = new ArrayList() {
        {
            add("192.168.1.1");
            add("192.168.1.2");
            add("192.168.1.3");
        }
    };
}
