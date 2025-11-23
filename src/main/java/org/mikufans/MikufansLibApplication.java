package org.mikufans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.mikufans", "org.tetofans"})
public class MikufansLibApplication {

  public static void main(String[] args) {
    SpringApplication.run(MikufansLibApplication.class, args);
  }

}
