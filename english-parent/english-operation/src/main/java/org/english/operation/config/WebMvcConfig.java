package org.english.operation.config;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.english.operation.interceptor.TokenInterceptor;
import org.english.operation.utils.SpringContextUtil;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * Created by sungang on 2016/12/15.
 */	
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private TokenInterceptor tokenInterceptor;
	
	
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	SpringContextUtil.setApplicationContext(applicationContext);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

 
    /**
     * 注册ViewController
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * 注册拦截器
     *
     * @param registry
     */
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/static/**").excludePathPatterns("/login").excludePathPatterns("/reg");
  
    }
    
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }
//
//    @Bean
//    public DateMessageConverter dateConverter() {
//        return new DateMessageConverter();
//    }

    
    @Bean
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.getSerializerProvider().setNullValueSerializer(
				new JsonSerializer<Object>() {
					@Override
					public void serialize(Object value, JsonGenerator jgen,
							SerializerProvider provider) throws IOException,
							JsonProcessingException {
						if(value instanceof Number){
							jgen.writeNumber(0);
						}
						else {
							jgen.writeString("");
						}
						
					}
				});
		jsonConverter.setObjectMapper(objectMapper);
//		List<MediaType> list = new ArrayList<MediaType>();
//		list.add(MediaType.APPLICATION_JSON);
//		jsonConverter.setSupportedMediaTypes(list);
		
		return jsonConverter;
	}
    
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
         configurer.favorPathExtension(false);
     }

	
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        super.configureMessageConverters(converters);
        //1.需要先定义一个convert 转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息,比如，是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
        		SerializerFeature.PrettyFormat,
        		SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty
				
        );
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
       // 4.将convert添加到converters当中
        converters.add(fastConverter);

    }
    
  //跨域配置
    @Override
	public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")  
        .allowedOrigins("*")  
      .allowedMethods("PUT", "DELETE","GET","POST")  
        .allowedHeaders("*")  
      .exposedHeaders("access-control-allow-headers",  
              "access-control-allow-methods",  
              "access-control-allow-origin",  
              "access-control-max-age",  
              "X-Frame-Options")  
      .allowCredentials(false).maxAge(3600);  
	}
    
    /**
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }
    
}
