package com.test.byteBuddy;

import lombok.ToString;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author zhouj
 * @since 2020-11-25
 */
public class ToStringAgent {
    public static void premain(String arguments, Instrumentation instrumentation) {

//        new AgentBuilder.Default()
//                .type(isAnnotatedWith(ToString.class))
//                .transform(new AgentBuilder.Transformer() {
//                    @Override
//                    public DynamicType.Builder transform(DynamicType.Builder builder,
//                                                         TypeDescription typeDescription,
//                                                         ClassLoader classloader) {
//                        return builder.method(named("toString"))
//                                .intercept(FixedValue.value("transformed"));
//                    }
//                }).installOn(instrumentation);
    }

}