package io.goldammer.spring_security_enum_roles_example.expression_handler;

import io.goldammer.spring_security_enum_roles_example.Role;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.METHOD)
@Retention(RUNTIME)
@PreAuthorize("hasRequiredRole()")
public @interface HasRequiredRole {
    Role[] roles();
}