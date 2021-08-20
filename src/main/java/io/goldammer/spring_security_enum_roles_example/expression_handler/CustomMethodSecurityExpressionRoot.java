package io.goldammer.spring_security_enum_roles_example.expression_handler;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private static final Logger LOGGER = Logger.getLogger(CustomMethodSecurityExpressionRoot.class.getSimpleName());

    private Object filterObject;
    private Object returnObject;
    private final MethodInvocation invocation;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        super(authentication);
        LOGGER.info("CustomMethodSecurityExpressionRoot");
        this.invocation = invocation;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }

    private HasRequiredRole getAnnotation(){
        return invocation.getMethod().getAnnotation(HasRequiredRole.class);
    }

    public boolean hasRequiredRole(String role) {
        LOGGER.info("hasRequiredRole(" + role + ")");
        return hasRequiredRole(Collections.singletonList(role));
    }

    public boolean hasRequiredRole() {
        LOGGER.info("hasRequiredRole()");
        return hasRequiredRole(Arrays.stream(getAnnotation().roles()).map(Enum::name).collect(Collectors.toList()));
    }

    public boolean hasRequiredRole(List<String> roles) {
        LOGGER.info(authentication.getAuthorities().toString() + " hasRequiredRole(" + roles.toString()+")");
        return authentication.getAuthorities().stream().anyMatch(auth -> roles.contains(auth.getAuthority()));
    }

}
