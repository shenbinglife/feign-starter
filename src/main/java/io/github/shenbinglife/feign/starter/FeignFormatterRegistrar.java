package io.github.shenbinglife.feign.starter;


import org.springframework.format.FormatterRegistrar;
import org.springframework.format.support.FormattingConversionService;

/**
 * Allows an application to customize the Feign {@link FormattingConversionService}.
 *
 * @author Matt Benson
 */
public interface FeignFormatterRegistrar extends FormatterRegistrar {

}

