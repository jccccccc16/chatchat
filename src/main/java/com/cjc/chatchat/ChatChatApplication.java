package com.cjc.chatchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cjc.chatchat.mapper")
public class ChatChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatChatApplication.class, args);
    }

}
