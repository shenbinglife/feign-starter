package io.github.shenbinglife.feign.starter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class FeignClientSpecification implements NamedContextFactory.Specification {

    private String name;

    private Class<?>[] configuration;

}