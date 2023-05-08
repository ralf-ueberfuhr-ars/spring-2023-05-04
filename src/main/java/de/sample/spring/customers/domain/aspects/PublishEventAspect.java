package de.sample.spring.customers.domain.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.function.Function;

@Aspect
@Component
@RequiredArgsConstructor
public class PublishEventAspect {

    private final ApplicationEventPublisher eventPublisher;

    // we can annotate the method directly
    @Around("@annotation(PublishEvent)")
    public Object publishEventForAnnotatedMethod(ProceedingJoinPoint jp) throws Throwable {
        return publishEvent(
          jp,
          m -> AnnotationUtils.findAnnotation(m, PublishEvent.class)
        );
    }

    // we can annotate the class, so each method is publishing the event
    @Around("@within(PublishEvent)")
    public Object publishEventForAnnotatedClass(ProceedingJoinPoint jp) throws Throwable {
        return publishEvent(
          jp,
          m -> AnnotationUtils.findAnnotation(m.getDeclaringClass(), PublishEvent.class)
        );
    }

    private Object publishEvent(ProceedingJoinPoint jp, Function<Method, PublishEvent> annotationFn) throws Throwable {
        if (jp.getSignature() instanceof MethodSignature ms) {
            final var parameters = jp.getArgs();
            final var method = ms.getMethod();
            final var annotation = annotationFn.apply(method);
            final var eventClass = annotation.value();
            final var eventConstructor = parameters.length == 0
              ? eventClass.getConstructor()
              : eventClass.getDeclaredConstructors()[0];
            try {
                return jp.proceed();
            } finally {
                final var event = eventConstructor.newInstance(parameters);
                eventPublisher.publishEvent(event);
            }
        } else {
            return jp.proceed();
        }
    }

}
