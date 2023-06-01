package ibf2022.batch3.assessment.csf.orderbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig {

    @Bean
    public WebMvcConfigurer configureCors() {
        return new EnableCors("/api/*", "*");
    }
    
}
