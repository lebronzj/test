package com.test.agent;

import lombok.SneakyThrows;

import java.lang.instrument.Instrumentation;

/**
 * @author zhouj
 * @since 2020-10-29
 */
public class Agent {

    @SneakyThrows
    public static void agentmain(String[] args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new ClassFileTest());
        instrumentation.retransformClasses(instrumentation.getAllLoadedClasses());
    }
}
