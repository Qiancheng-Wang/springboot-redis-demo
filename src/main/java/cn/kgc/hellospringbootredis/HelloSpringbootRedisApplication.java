package cn.kgc.hellospringbootredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@RestController
@MapperScan("cn.kgc.hellospringbootredis.mapper")
public class HelloSpringbootRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringbootRedisApplication.class, args);
	}

	@GetMapping("/hello")
	public String test(){
		return "All good";
	}
}
