package com.test.sa;

/**
 * @author zhouj
 * @since 2020-12-09
 */
public class Wukong {

    public static int wk_static_i = 7777777;
    private int wk_instance_i;

    public Wukong(int wk_instance_i) {
        this.wk_instance_i = wk_instance_i;
    }
    public int getWk_instance_i() {
        return wk_instance_i;
    }
}
