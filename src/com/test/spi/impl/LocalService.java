package com.test.spi.impl;

import com.test.spi.IService;

/**
 * @author zhouj
 * @since 2020-11-09
 */
public  class LocalService  implements IService {
    @Override
    public String sayHello() {
        return "Hello LocalService";
    }
    @Override
    public String getScheme() {
        return "local";
    }
}