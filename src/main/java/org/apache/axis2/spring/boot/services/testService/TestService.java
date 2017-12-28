package org.apache.axis2.spring.boot.services.testService;

import org.springframework.stereotype.Component;

/**
 * Created by yuebo on 2017/12/13.
 */
@Component
public class TestService {
    public String sayHello(){
        return "hello";
    }
}
