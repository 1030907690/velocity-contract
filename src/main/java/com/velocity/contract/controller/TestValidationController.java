package com.velocity.contract.controller;

import com.velocity.contract.vo.ValidationBean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestValidationController {

    @RequestMapping("/validation")
    public String validation(@Validated @RequestBody ValidationBean validationBean){
        System.out.println(validationBean);
        return "";
    }
}
