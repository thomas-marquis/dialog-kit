package com.dialogkit.core.processor;

import com.dialogkit.core.annotations.BotConfiguration;
import com.dialogkit.core.exceptions.InvalidBotConfigException;
import com.google.auto.service.AutoService;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {
    "com.dialogkit.core.annotations.*"
})
public class AnnotationsProcessor extends AbstractProcessor {

    private static final Integer FIRST = 0;

    private RoundEnvironment roundEnv;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations == null || annotations.isEmpty()) {
            return false;
        }
        this.roundEnv = roundEnv;
        annotations.forEach(this::processElement);
        return true;
    }

    private void processElement(TypeElement element) throws InvalidBotConfigException {
        // find configuration class (only one class or throw)
        List<BotConfiguration> botConfigurations = roundEnv.getElementsAnnotatedWith(element).stream()
            .map(elt -> elt.getAnnotation(BotConfiguration.class))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        if (botConfigurations.size() != 1) {
            throw new InvalidBotConfigException("need only one bot configuration class");
        }
        BotConfiguration botConfigurationAnnotation = botConfigurations.get(FIRST);


        // find intents classes
    }
}
