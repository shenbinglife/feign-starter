package io.github.shenbinglife.feign.starter.annotation;

import feign.MethodMetadata;
import io.github.shenbinglife.feign.starter.AnnotatedParameterProcessor;
import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.annotation.Annotation;
import java.util.Collection;

import static feign.Util.checkState;
import static feign.Util.emptyToNull;

/**
 * {@link RequestHeader} parameter processor.
 *
 * @author Jakub Narloch
 * @see AnnotatedParameterProcessor
 */
public class RequestHeaderParameterProcessor implements AnnotatedParameterProcessor {

    private static final Class<RequestHeader> ANNOTATION = RequestHeader.class;

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return ANNOTATION;
    }

    @Override
    public boolean processArgument(AnnotatedParameterContext context, Annotation annotation) {
        String name = ANNOTATION.cast(annotation).value();
        checkState(emptyToNull(name) != null,
                "RequestHeader.value() was empty on parameter %s", context.getParameterIndex());
        context.setParameterName(name);

        MethodMetadata data = context.getMethodMetadata();
        Collection<String> header = context.setTemplateParameter(name, data.template().headers().get(name));
        data.template().header(name, header);
        return true;
    }
}
