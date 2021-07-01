package com.test.balance;

import lombok.Data;

/**
 * @author zhouj
 * @since 2020-08-14
 */
@Data
public class Server {

    public Server(int weight, int currentWeight, String ip) {
        this.weight = weight;
        this.currentWeight = currentWeight;
        this.ip = ip;
    }

    private int weight;

    private int currentWeight;

    private String ip;
}
