package com.springboot2.api.config;
//
//package com.grokonez.jwtauthentication.security;
//
//
//import com.demo.spring.constants.RestApiVersioningConstant;
//import java.lang.reflect.Method;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
///**
// *
// * @author Mustafa
// */
//@Configuration
//public class RestApiVersioningConfig  {
// 
//    @Bean
//    public WebMvcRegistrationsAdapter webMvcRegistrationsHandlerMapping() {
//        return new WebMvcRegistrationsAdapter() {
//            @Override
//            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//                return new RequestMappingHandlerMapping() {
//                    private final static String API_BASE_PATH = RestApiVersioningConstant.API_BASE_PATH+RestApiVersioningConstant.API_VERSION;
// 
//                    @Override
//                    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
//                        Class<?> beanType = method.getDeclaringClass();
//                        if (AnnotationUtils.findAnnotation(beanType, RestController.class) != null) {
//                            PatternsRequestCondition apiPattern = new PatternsRequestCondition(StringUtils.deleteWhitespace(API_BASE_PATH))
//                                    .combine(mapping.getPatternsCondition());
// 
//                            mapping = new RequestMappingInfo(mapping.getName(), apiPattern,
//                                    mapping.getMethodsCondition(), mapping.getParamsCondition(),
//                                    mapping.getHeadersCondition(), mapping.getConsumesCondition(),
//                                    mapping.getProducesCondition(), mapping.getCustomCondition());
//                        }
// 
//                        super.registerHandlerMethod(handler, method, mapping);
//                    }
//                };
//            }
//        };
//    }
// 
//}
