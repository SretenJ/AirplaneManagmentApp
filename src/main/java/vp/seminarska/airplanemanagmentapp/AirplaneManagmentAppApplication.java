package vp.seminarska.airplanemanagmentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication()
@ConfigurationPropertiesScan
public class AirplaneManagmentAppApplication {
    @Bean
    public RestTemplate getRestTemplates()
    {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(AirplaneManagmentAppApplication.class, args);
    }

}
