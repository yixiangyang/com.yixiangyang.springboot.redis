package com.yixiangyang.springboot.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@SpringBootApplication
@MapperScan("com.yixiangyang.springboot.mapper")
@ComponentScan(basePackages={"com.yixiangyang.springboot.config","com.yixiangyang.springboot.controller","com.yixiangyang.springboot.service"})
public class Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("------------分隔符-------------");
		System.out.println(System.getProperties());
	}
	
}
