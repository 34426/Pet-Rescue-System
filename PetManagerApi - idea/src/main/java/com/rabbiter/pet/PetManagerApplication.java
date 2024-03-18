package com.rabbiter.pet;

import com.rabbiter.pet.utils.PathUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetManagerApplication {

    public static void main(String[] args) {
        System.out.println("Project Path : " + PathUtils.getClassLoadRootPath());
        SpringApplication.run(PetManagerApplication.class, args);
    }

}
