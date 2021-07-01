package com.test.sa;

import sun.jvm.hotspot.debugger.JVMDebugger;
import sun.jvm.hotspot.debugger.MachineDescriptionIntelX86;
import sun.jvm.hotspot.debugger.bsd.BsdDebuggerLocal;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.runtime.VM;

/**
 * @author zhouj
 * @since 2020-12-09
 */
public class HotspotApiTest extends sun.jvm.hotspot.tools.Tool {

    public static void main(String args[]) {
        JVMDebugger debugger = new BsdDebuggerLocal(new MachineDescriptionIntelX86(), true);
        debugger.attach(60503);
        HotspotApiTest ht = new HotspotApiTest(debugger);
        ht.start();
        ht.stop();
    }

    public HotspotApiTest(JVMDebugger jvmDebugger) {
        super(jvmDebugger);
    }

    @Override
    public void run() {
        VM vm = VM.getVM();
        final ObjectHeap objectHeap = vm.getObjectHeap();
        objectHeap.iterate(new HeapVisitor() {
            @Override
            public void prologue(long l) {
            }

            @Override
            public boolean doObj(Oop oop) {
                System.out.println("////////////////////////////////////////");
                System.out.println("OOP#" + oop);
                oop.iterate(new OopPrinter(System.out), true);
                System.out.println("////////////////////////////////////////");
                System.out.println("OOP.KLASS#" + oop.getKlass());
//                oop.getKlass().iterateFields(new OopPrinter(System.out), true);
//                System.out.println("////////////////////////////////////////");
//                System.out.println("OOP.KLASS.MIRROR#" + oop.getKlass().getJavaMirror());
//                oop.getKlass().getJavaMirror().iterate(new OopPrinter(System.out), true);
//                System.out.println("////////////////////////////////////////");
//                System.out.println("OOP.KLASS.KLASS#" + oop.getKlass().getKlass());
//                oop.getKlass().arrayKlass().iterate(new OopPrinter(System.out), true);
//                System.out.println("////////////////////////////////////////");
//                System.out.println("OOP.KLASS.KLASS.KLASS#" + oop.getKlass().getKlass().getKlass());
//                oop.getKlass().getKlass().getKlass().iterate(new OopPrinter(System.out), true);
//                System.out.println("////////////////////////////////////////");
//                System.out.println("OOP.KLASS.KLASS.KLASS.KLASS#" + oop.getKlass().getKlass().getKlass().getKlass());
//                oop.getKlass().getKlass().getKlass().getKlass().iterate(new OopPrinter(System.out), true);
                return false;
            }

            @Override
            public void epilogue() {
            }

        }, new ObjectHeap.ObjectFilter() {
            @Override
            public boolean canInclude(Oop oop) {
                Klass klass = oop.getKlass();
                return klass.getName() != null && "hotspot/api/test/Wukong".equals(klass.getName().asString());
            }
        });
    }

}

