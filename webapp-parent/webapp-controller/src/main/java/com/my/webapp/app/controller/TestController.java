package com.my.webapp.app.controller;

import com.my.webapp.app.controller.param.EncryptParam;
import com.my.webapp.app.controller.response.Response;
import com.my.webapp.common.pojo.User;
import com.my.webapp.redis.RedisManager;
import com.my.webapp.redis.RedisService;
import com.my.webapp.redis.constants.RedisKeyPrefix;
import com.my.webapp.service.ExceptionService;
import io.swagger.annotations.*;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Api(tags = "测试", produces = MediaType.APPLICATION_JSON_VALUE)//整个类的说明
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.profiles.active}")
    private String profileActive;

    @Autowired
    private StringEncryptor jasyptStringEncryptor;
    @ApiOperation(value = "加密", produces = MediaType.APPLICATION_JSON_VALUE)//方法说明
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功", response = Response.class)})//响应数据说明，可以有多个
    @RequestMapping(value = "/encrypt", method = RequestMethod.POST)
    public Response encrypt(@RequestBody @Valid EncryptParam param) {
        return Response.success(jasyptStringEncryptor.encrypt(param.getText()));
    }

    @RequestMapping(value = "/decrypt/{cipherText}", method = RequestMethod.POST)
    public Response decrypt(@PathVariable @ApiParam(value = "密文", required = true) String cipherText) {
        return Response.success(jasyptStringEncryptor.decrypt(cipherText));
    }

    @Autowired
    private ExceptionService exceptionService;
    @RequestMapping(value = "/other", method = RequestMethod.GET)
    public Response otherTest() throws Exception{
        exceptionService.businessExceptionTest();
        return Response.success(null);
    }

    @Autowired
    private RedisService redisService;
    @RequestMapping(value = "/redisTest/{key}/{value}", method = RequestMethod.GET)
    public Response redisTest(@PathVariable String key, @PathVariable String value) throws Exception{

        List<User> users = new ArrayList<User>();
        User u1 = new User("测试1", 12);
        User u2 = new User("测试2", 26);
        users.add(u1);
        users.add(u2);
       // redisService.setObject(key, users);
        List<User> u = redisService.getList(key, User.class);
        logger.debug(u.get(0).getUserName());
        logger.debug(u.get(0).getYear()+"");
        return Response.success(null);
    }

}
