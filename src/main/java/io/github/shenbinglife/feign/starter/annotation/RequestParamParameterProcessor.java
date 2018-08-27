package io.github.shenbinglife.feign.starter.annotation;


import feign.MethodMetadata;
import io.github.shenbinglife.feign.starter.AnnotatedParameterProcessor;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.util.Collection;

import static feign.Util.emptyToNull;

/**
 * {@link RequestParam} parameter processor.
 *
 * @author Jakub Narloch
 * @see AnnotatedParameterProcessor
 */
public class RequestParamParameterProcessor implements AnnotatedParameterProcessor {

    private static final Class<RequestParam> ANNOTATION = RequestParam.class;

    @Override
    public Class<? extends Annotation> getAnnotationType() {
        return ANNOTATION;
    }

    @Override
    public boolean processArgument(AnnotatedParameterProcessor.AnnotatedParameterContext context,
                    Annotation annotation) {
        RequestParam requestParam = ANNOTATION.cast(annotation);
        String name = requestParam.value();
        if (emptyToNull(name) != null) {
            context.setParameterName(name);

            MethodMetadata data = context.getMethodMetadata();
            Collection<String> query = context.setTemplateParameter(name, data.template().queries().get(name));
            data.template().query(name, query);
        } else {
            // supports `Map` types
            MethodMetadata data = context.getMethodMetadata();
            data.queryMapIndex(context.getParameterIndex());
        }
        return true;
    }
}
