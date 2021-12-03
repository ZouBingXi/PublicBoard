package cn.szuer.publicboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan(basePackages="cn.szuer.publicboard.mapper")
public class PublicboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicboardApplication.class, args);
	}

}

