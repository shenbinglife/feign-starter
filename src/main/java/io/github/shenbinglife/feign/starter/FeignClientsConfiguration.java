package io.github.shenbinglife.feign.starter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import io.github.shenbinglife.feign.starter.support.ResponseEntityDecoder;
import io.github.shenbinglife.feign.starter.support.SpringDecoder;
import io.github.shenbinglife.feign.starter.support.SpringEncoder;
import io.github.shenbinglife.feign.starter.support.SpringMvcContract;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/5/21
 * @since since
 */
@Configuration
public class FeignClientsConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Autowired(required = false)
    private List<AnnotatedParameterProcessor> parameterProcessors = new ArrayList<>();

    @Autowired(required = false)
    private List<FeignFormatterRegistrar> feignFormatterRegistrars = new ArrayList<>();

    @Autowired(required = false)
    private Logger logger;

    @Bean
    @ConditionalOnMissingBean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
    }

    @Bean
    @ConditionalOnMissingBean
    public Encoder feignEncoder() {
        return new SpringEncoder(this.messageConverters);
    }

    @Bean
    @ConditionalOnMissingBean
    public Contract feignContract(ConversionService feignConversionService) {
        return new SpringMvcContract(this.parameterProcessors, feignConversionService);
    }

    @Bean
    public FormattingConversionService feignConversionService() {
        FormattingConversionService conversionService = new DefaultFormattingConversionService();
        for (FeignFormatterRegistrar feignFormatterRegistrar : feignFormatterRegistrars) {
            feignFormatterRegistrar.registerFormatters(conversionService);
        }
        return conversionService;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ConditionalOnMissingBean
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

}
