package br.com.mpetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@SpringBootApplication
public class MpetechApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MpetechApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MpetechApplication.class);
    }

}
