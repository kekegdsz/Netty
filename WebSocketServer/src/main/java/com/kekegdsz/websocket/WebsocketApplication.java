package com.kekegdsz.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
        //启动netty
        try {
            WSServer.startNetty();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
