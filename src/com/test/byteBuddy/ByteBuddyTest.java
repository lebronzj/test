package com.test.byteBuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

/**
 * @author zhouj
 * @since 2020-11-25
 */
public class ByteBuddyTest {

    public static void main(String[] args) {
        DynamicType.Unloaded unloaded = new ByteBuddy().subclass(Object.class).make();
    }
}
