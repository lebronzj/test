package com.test.calculate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouj
 * @since 16/11/30
 */
public class TestBigDecimal {
    public static void main(String[] args) {
        BigDecimal b = new BigDecimal(8);
        BigDecimal c = new BigDecimal(2.5);
        BigDecimal d = b.divide(c,0,BigDecimal.ROUND_DOWN);
        System.out.println(d.intValue());
        BigDecimal bigDecimal = new BigDecimal(((Double)333.323).toString());
        Map<String,Object> map = new HashMap<>();
        map.put("ss",443.43434);
        map.put("decimal",bigDecimal);
        System.out.println(map.get("ss").toString());
        System.out.println("bigDecimal:"+bigDecimal);
        System.out.println("bigDecimal:"+map.get("decimal").toString());
    }
}
