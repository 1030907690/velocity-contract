package com.velocity.contract.controller;

import com.velocity.contract.utils.VelocityInitializer;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.io.IOUtils;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.deepoove.poi.XWPFTemplate;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ContractController {

    @RequestMapping("/")
    public String root() {

        VelocityInitializer.initVelocity();

        VelocityContext context = new VelocityContext();
        context.put("name", "张三");
        context.put("code", "5200214422");
        String template = "src/main/resources/vm/test.xml";

        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate(template, "UTF-8");
        tpl.merge(context, sw);
        System.out.println("sw " + sw.toString());

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

        try {


            // 填充数据完毕的test.docx，在转换成图片
            File file1 = new File("D:\\work\\velocity-contract\\gen.docx");
            // 打开生成的 Word 文件
            Document doc = new Document(Files.newInputStream(file1.toPath()));
            String filePath = "D:\\work\\velocity-contract\\";


            System.out.println("page " + doc.getPageCount());
            // 逐页将 Word 文件保存为图片（PNG格式）
            for (int i = 0; i < doc.getPageCount(); i++) {
                String pathPre = "gen_" + i + ".png";
                Document extractedPage = doc.extractPages(i, 1);
                // 拼接上文件名
                String path = filePath + pathPre;
                // 将 Word 文件保存为图片PNG格式
                extractedPage.save(path, SaveFormat.PNG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/";
    }


    /**
     * word文档需要填充的数据
     *
     * @return
     */
    public static Map<String, Object> initWordMap() {
        Map<String, Object> wordData = new HashMap<>();
        wordData.put("name", "张三");
        wordData.put("code", "5200214422");

        return wordData;
    }

    @RequestMapping("/test2")
    public String test2() {

        try {
            // 获取word文档
            File file = new File("D:\\work\\velocity-contract\\src\\main\\resources\\vm\\test2.docx");
            // 读取文件
            InputStream ins = Files.newInputStream(file.toPath());
            // 使用模板引擎将模板渲染，并传入一个数据映射表 initWordMap()。
            XWPFTemplate template = XWPFTemplate.compile(ins).render(initWordMap());
            // 将模板渲染后保存为新的 Word 文件
            template.writeToFile("D:\\work\\velocity-contract\\test.docx");

            // 填充数据完毕的test.docx，在转换成图片
            File file1 = new File("D:\\work\\velocity-contract\\test.docx");
            // 打开生成的 Word 文件
            Document doc = new Document(Files.newInputStream(file1.toPath()));
            String filePath = "D:\\work\\velocity-contract\\";


            System.out.println("page " + doc.getPageCount());
            // 逐页将 Word 文件保存为图片（PNG格式）
            for (int i = 0; i < doc.getPageCount(); i++) {
                String pathPre = "test_" + i + ".png";
                Document extractedPage = doc.extractPages(i, 1);
                // 拼接上文件名
                String path = filePath + pathPre;
                // 将 Word 文件保存为图片PNG格式
                extractedPage.save(path, SaveFormat.PNG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "2";
    }


}
