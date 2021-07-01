package com.test.classLoader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author zhouj
 * @since 2021-06-15
 */
public class MyUrlClassLoader extends URLClassLoader {
    public MyUrlClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
}
