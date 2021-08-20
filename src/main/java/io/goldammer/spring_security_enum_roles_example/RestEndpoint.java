package io.goldammer.spring_security_enum_roles_example;

import io.goldammer.spring_security_enum_roles_example.expression_handler.HasRequiredRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEndpoint {

    @GetMapping("/test-1")
    @PreAuthorize("hasRequiredRole('TEST_ONE_READ')")
    String test1(){
        return "test1";
    }

    @GetMapping("/test-11")
    @PreAuthorize("hasRequiredRole({'TEST_ONE_READ', 'Test_TWO'})")
    String test11(){
        return "test11";
    }

    @GetMapping("/test-2")
    @HasRequiredRole(roles = {Role.TEST_TWO})
    String test2(){
        return "test2";
    }

}