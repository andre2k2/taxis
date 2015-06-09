package br.com.ecoder.taxis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import br.com.ecoder.taxis.interceptor.AuthenticationInterceptor;

@Configuration
@ComponentScan(basePackages = "br.com.ecoder.taxis")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {

        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();

        // Habilita o uso de MatrixParams
        handlerMapping.setRemoveSemicolonContent(false);
        handlerMapping.setUseSuffixPatternMatch(false);

        // Injeta os interceptors
        handlerMapping.setInterceptors(new Object[] { authenticationInterceptor });

        return handlerMapping;
    }

}
