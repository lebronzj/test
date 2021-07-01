package com.test.jmx;

/**
 * @author zhouj
 * @since 2020-12-03
 */
public interface HelloWorldMBean  {

    String getGreeting();

    void setGreeting(String greeting);

    void printGreeting();
}
