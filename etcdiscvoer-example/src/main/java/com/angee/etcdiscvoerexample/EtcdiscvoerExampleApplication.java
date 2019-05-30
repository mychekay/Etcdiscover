package com.angee.etcdiscvoerexample;

import com.angee.annotation.EnableEtcdClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//enable register
@EnableEtcdClient
public class EtcdiscvoerExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtcdiscvoerExampleApplication.class, args);
	}

}
