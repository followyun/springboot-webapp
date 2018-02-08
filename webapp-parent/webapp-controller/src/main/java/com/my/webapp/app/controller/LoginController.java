package com.my.webapp.app.controller;

import com.my.webapp.app.controller.response.Response;
import com.my.webapp.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping("/user")
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoginService loginService;
    @Value("${spring.profiles.active}")
    private String profileActive;


    @Value("${test.value}")
    private String testValue;

    @RequestMapping(value = "/login/{name}", method = RequestMethod.GET)
    public String login(@PathVariable("name") String name) {
        System.out.println(name);
        switch (name){
            case ST:
                System.out.println(name);
                break;
                default:
                    break;
        }
        return "welcome to spring boot world！";
    }

    @RequestMapping(value = "/loginWithService/{name}", method = RequestMethod.GET)
    public Response loginWithService(@PathVariable("name") String name) {
        System.out.println(name);
        System.out.println("profileActive: " + profileActive);
        loginService.login();
        logger.info("test.value: " + testValue);
        return Response.success(testValue);
    }
    private final static String ST = "123";


}
