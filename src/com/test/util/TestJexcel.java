package com.test.util;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther zhouj
 * @since 2018/9/7
 */
public class TestJexcel {

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<String,Object>();
        JexlEngine jexlEngine = new JexlEngine();
        Expression expression = jexlEngine.createExpression("name.matches('小.') && age>38 && gender.equals('男') && address.matches('深圳.*')");
        map.put("name", "小王");
        map.put("age", 39);
        map.put("gender", "男");
        map.put("address", "深圳市南山区南头街道");
        JexlContext content = new MapContext(map);
        System.out.println(expression.evaluate(content));
    }
}
