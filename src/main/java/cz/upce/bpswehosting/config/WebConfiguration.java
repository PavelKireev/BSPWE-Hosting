package cz.upce.bpswehosting.config;

import com.fasterxml.jackson.databind.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
public class WebConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
            .featuresToDisable(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                SerializationFeature.FAIL_ON_EMPTY_BEANS,
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
            )
            .propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
            .build();
    }
}
