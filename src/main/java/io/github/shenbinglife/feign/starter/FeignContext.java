package io.github.shenbinglife.feign.starter;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/5/21
 * @since since
 */
public class FeignContext extends NamedContextFactory<FeignClientSpecification> {

    public FeignContext() {
        super(FeignClientsConfiguration.class, "feign", "feign.client.name");
    }

}
