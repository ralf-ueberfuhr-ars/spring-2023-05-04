package de.sample.spring.customers.domain.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a method with this aspect to get an event published after
 * method execution. The given class is the event type and must have a single constructor
 * that matches the annotated method's parameters.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishEvent {

    Class<?> value();

}
