package com.test.sa;

import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.tools.jcore.ClassFilter;

/**
 * @author zhouj
 * @since 2020-12-04
 */
public class MyFilter implements ClassFilter {

    @Override
    public boolean canInclude(InstanceKlass kls) {
        String klassName = kls.getName().asString();
        return klassName.startsWith("com/fr/license/selector/");
    }
}