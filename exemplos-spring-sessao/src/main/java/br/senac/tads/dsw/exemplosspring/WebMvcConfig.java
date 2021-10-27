package br.senac.tads.dsw.exemplosspring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Autowired
//    public MessageSource messageSource;
//
//    @Bean(name = "localeResolver")
//    public CookieLocaleResolver localeResolver() {
//        CookieLocaleResolver clr = new CookieLocaleResolver();
//        clr.setDefaultLocale(new Locale("pt", "BR"));
//        return clr;
//    }
//
//    // Interceptador que permite trocar manualmente o idioma usado
//    @Bean(name = "localeChangeInterceptor")
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return lci;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }

    /*
     * Configura as mensagens para serem usadas com Bean Validation<br>
     * Referência:
     * https://www.baeldung.com/spring-custom-validation-message-source
     */
//    @Bean(name = "messageSource")
//    public ReloadableResourceBundleMessageSource messageSource() {
//        ReloadableResourceBundleMessageSource source
//                = new ReloadableResourceBundleMessageSource();
//        source.setBasename("classpath:i18n/mensagens");
//        source.setDefaultEncoding("UTF-8");
//        source.setCacheSeconds(0);
//        return source;
//    }

//    @Bean
//    public LocalValidatorFactoryBean getValidator() {
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//        // bean.setValidationMessageSource(messageSource());
//        bean.setValidationMessageSource(messageSource);
//        return bean;
//    }

}
