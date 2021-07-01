package com.test.sa;

import lombok.SneakyThrows;
import sun.jvm.hotspot.BsdVtblAccess;
import sun.jvm.hotspot.HotSpotTypeDataBase;
import sun.jvm.hotspot.debugger.JVMDebugger;
import sun.jvm.hotspot.debugger.MachineDescription;
import sun.jvm.hotspot.debugger.MachineDescriptionIntelX86;
import sun.jvm.hotspot.debugger.bsd.BsdDebuggerLocal;
import sun.jvm.hotspot.gc_implementation.parallelScavenge.PSOldGen;
import sun.jvm.hotspot.gc_implementation.parallelScavenge.ParallelScavengeHeap;
import sun.jvm.hotspot.gc_implementation.shared.MutableSpace;
import sun.jvm.hotspot.gc_interface.CollectedHeap;
import sun.jvm.hotspot.memory.Universe;
import sun.jvm.hotspot.oops.HeapPrinter;
import sun.jvm.hotspot.oops.HeapVisitor;
import sun.jvm.hotspot.oops.ObjectHeap;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.tools.Tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.stream.Collectors;

/**
 * sudo java -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home/lib/tools.jar:/Users/zhouj/zhou/test/target/classes: com.test.sa.TestPrintPSPermGen
 *
 * @author zhouj
 * @since 2020-12-09
 */
public class TestPrintPSPermGen extends Tool {
    @SneakyThrows
    public static void main(String[] args) {
//        Process process = Runtime.getRuntime().exec("pwd");
//        System.out.println(new BufferedReader(new InputStreamReader(process.getInputStream())).lines().collect(Collectors.joining()));
//        MachineDescription machineDescription = new MachineDescriptionIntelX86();
//        JVMDebugger debugger = new BsdDebuggerLocal(machineDescription, true);
//        BsdVtblAccess bsdVtblAccess = new BsdVtblAccess(debugger, args);
//        HotSpotTypeDataBase hotSpotTypeDataBase = new HotSpotTypeDataBase(machineDescription, bsdVtblAccess, debugger, args);

//        debugger.attach(15074);
        String[] strings = {"60193"};
        TestPrintPSPermGen test = new TestPrintPSPermGen();
//        TestPrintPSPermGen test = new TestPrintPSPermGen(debugger);
        test.execute(strings);
        test.stop();
    }

//    public TestPrintPSPermGen(JVMDebugger jvmDebugger) {
//        super(jvmDebugger);
//    }

    @Override
    public void run() {
        VM vm = VM.getVM();
        Universe universe = vm.getUniverse();
        CollectedHeap heap = universe.heap();
        puts("GC heap name: " + heap.kind());
        if (heap instanceof ParallelScavengeHeap) {
            ParallelScavengeHeap psHeap = (ParallelScavengeHeap) heap;
            PSOldGen perm = psHeap.oldGen();
            MutableSpace permObjSpace = perm.objectSpace();
            puts("Perm gen: [" + permObjSpace.bottom() + ", " + permObjSpace.end() + ")");
            long permSize = 0;
            for (VM.Flag f : VM.getVM().getCommandLineFlags()) {
                if ("PermSize".equals(f.getName())) {
                    permSize = Long.parseLong(f.getValue());
                    break;
                }
            }
            puts("PermSize: " + permSize);
        }
        puts();

        ObjectHeap objHeap = vm.getObjectHeap();
        HeapVisitor heapVisitor = new HeapPrinter(System.out);
        objHeap.iterate(heapVisitor);
    }

    private static void puts() {
        System.out.println();
    }

    private static void puts(String s) {
        System.out.println(s);
    }
}
