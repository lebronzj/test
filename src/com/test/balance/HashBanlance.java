package com.test.balance;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author zhouj
 * @since 2020-08-14
 */
public class HashBanlance {

    public static List<String> list = new ArrayList() {
        {
            add("192.168.1.1");
            add("192.168.1.2");
            add("192.168.1.3");
        }
    };

    private static String go(String client) {
        int nodeCount = 20;
        TreeMap<Integer, String> treeMap = new TreeMap();
        for (String s : list) {
            for (int i = 0; i < nodeCount; i++){
                treeMap.put((s + "--服务器---" + i).hashCode(), s);
            }
        }
        int clientHash = client.hashCode();
        SortedMap<Integer, String> subMap = treeMap.tailMap(clientHash);
        Integer firstHash;
        if (subMap.size() > 0) {
            firstHash = subMap.firstKey();
        } else {
            firstHash = treeMap.firstKey();
        }
        String s = treeMap.get(firstHash);
        return s;
    }

    public static void main(String[] args) {
        System.out.println(go("今天天气不错啊"));
        System.out.println(go("192.168.5.258"));
        System.out.println(go("0"));
        System.out.println(go("-110000"));
        System.out.println(go("风雨交加"));
    }
}
