package com.test.sa;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import sun.jvm.hotspot.HotSpotAgent;

/**
 * @author zhouj
 * @since 2020-12-09
 */
public class SaAgent {

    public static void main(String[] args) {
        for (VirtualMachineDescriptor virtualMachine : VirtualMachine.list()) {
            System.out.println(virtualMachine.id());
            System.out.println(virtualMachine.displayName());
        }
//        HotSpotAgent hotSpotAgent = new HotSpotAgent();
//        hotSpotAgent.attach(19056);
//        hotSpotAgent.getDebugger();
//        hotSpotAgent.getTypeDataBase();
    }
}
