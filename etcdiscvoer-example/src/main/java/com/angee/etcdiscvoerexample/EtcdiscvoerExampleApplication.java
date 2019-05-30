package com.angee.etcdiscvoerexample;

import com.angee.annotation.EnableEtcdClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEtcdClient
public class EtcdiscvoerExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtcdiscvoerExampleApplication.class, args);
		System.out.println("hello world");
	}

}
