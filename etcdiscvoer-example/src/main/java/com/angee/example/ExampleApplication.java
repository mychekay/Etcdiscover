package com.angee.example;

import com.angee.annotation.EnableEtcdClient;
import com.angee.annotation.EnableEtcdDiscover;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEtcdClient
@EnableEtcdDiscover
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

}
