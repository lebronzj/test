package com.test.sa;

import sun.jvm.hotspot.runtime.VM;

/**
 * @author zhouj
 * @since 2020-12-09
 */
public class VMtest {
    public static void main(String[] args) {
        VM vm =VM.getVM();
        System.out.println(vm.getCPU());
    }
}
