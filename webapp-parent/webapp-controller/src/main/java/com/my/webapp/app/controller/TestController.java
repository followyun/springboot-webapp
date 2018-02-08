package com.my.webapp.app.controller;

import com.my.webapp.app.controller.param.EncryptParam;
import com.my.webapp.app.controller.response.Response;
import com.my.webapp.service.ExceptionService;
import io.swagger.annotations.*;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

}
