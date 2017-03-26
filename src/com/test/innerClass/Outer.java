package com.test.innerClass;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouj on 16/4/27.
 */
public class Outer {
    private final Map<String,Object> map = new HashMap<String, Object>();

    public static void main(String[] args) {

        TestInnerStatic testInnerStatic = new Outer.TestInnerStatic();
    }

    public class TestInner{

    }

    static class TestInnerStatic{

    }

    public Outer(){
        new TestInner();
    }

}
