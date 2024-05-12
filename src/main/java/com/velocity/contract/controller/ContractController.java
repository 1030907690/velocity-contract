package com.velocity.contract.controller;

import com.velocity.contract.utils.VelocityInitializer;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.io.IOUtils;

import java.io.*;

@RestController
public class ContractController {

    @RequestMapping("/")
    public String root(){

        VelocityInitializer.initVelocity();

        VelocityContext context = new VelocityContext();
        context.put("name", "张三");
        context.put("code", "5200214422");
        String template = "src/main/resources/vm/test.xml";

        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate(template, "UTF-8");
        tpl.merge(context, sw);
        System.out.println("sw " +sw.toString());

        OutputStream output = null;
        try {
            output = new FileOutputStream("gen.docx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            IOUtils.write(sw.toString(), output, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IOUtils.closeQuietly(sw);
        return "/";
    }




}
