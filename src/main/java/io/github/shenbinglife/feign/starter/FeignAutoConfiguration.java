package io.github.shenbinglife.feign.starter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Client;
import feign.Feign;
import feign.okhttp.OkHttpClient;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/5/21
 * @since since
 */
@Configuration
@ConditionalOnClass(Feign.class)
public class FeignAutoConfiguration {


    @Autowired(required = false)
    private List<FeignClientSpecification> configurations = new ArrayList<>();

    @Bean
    public FeignClientProperties feignClientProperties() {
        return new FeignClientProperties();
    }

    @Bean
    public FeignContext feignContext() {
        FeignContext context = new FeignContext();
        context.setConfigurations(this.configurations);
        return context;
    }

    @Configuration
    @ConditionalOnClass(OkHttpClient.class)
    protected static class OkHttpFeignConfiguration {

        @Autowired(required = false)
        private okhttp3.OkHttpClient okHttpClient;

        @Bean
        @ConditionalOnMissingBean(Client.class)
        public Client feignClient() {
            if (this.okHttpClient != null) {
                return new OkHttpClient(this.okHttpClient);
            }
            return new OkHttpClient();
        }
    }
}
