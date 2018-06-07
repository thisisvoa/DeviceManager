/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.geekcattle.netty.server.TCPServer;

import java.nio.charset.Charset;
import java.util.List;

@EnableWebMvc//启动MVC
@EnableTransactionManagement // 启注解事务管理
@SpringBootApplication//SpringBoot启动核心
@ImportResource(locations={"classpath:spring-config.xml"})
public class Application extends WebMvcConfigurerAdapter  {

    /**
     * 如果要发布到自己的Tomcat中的时候，需要继承SpringBootServletInitializer类，并且增加如下的configure方法。
     * 如果不发布到自己的Tomcat中的时候，就无需上述的步骤
     */
   /* @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.clas);
    }*/

    public static void main(String[] args){

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        //可以设置编码，默认UTF-8
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastConverter);
    }
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/static/404.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/static/500.html");
                container.addErrorPages(error404Page);
                container.addErrorPages(error500Page);
            }
        };
    }

}
