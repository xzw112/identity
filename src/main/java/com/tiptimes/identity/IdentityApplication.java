package com.tiptimes.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.util.unit.DataSize;
import tk.mybatis.spring.annotation.MapperScan;
import javax.servlet.MultipartConfigElement;

@MapperScan("com.tiptimes.identity.dao")
@SpringBootApplication(scanBasePackages = {"com.tiptimes.*"})
@EnableScheduling   //开启调度任务
public class IdentityApplication {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // Max size of one file
        factory.setMaxFileSize(DataSize.parse("1000MB")); //KB,MB
        // Max Size of All files
        factory.setMaxRequestSize(DataSize.parse("1000MB"));
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
    }


    @Bean
public HttpFirewall allowUrlSemicolonHttpFirewall() {
    StrictHttpFirewall firewall = new StrictHttpFirewall();
    firewall.setAllowSemicolon(true);
    return firewall;
}
}
