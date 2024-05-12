package com.velocity.contract.utils;


import java.util.Properties;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * VelocityEngine工厂
 * 
 */
public class VelocityInitializer
{
    /**
     * 初始化vm方法
     */
    public static void initVelocity()
    {
        Properties p = new Properties();
        try
        {
            // 加载classpath目录下的vm文件
//            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//            盘路径
            p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "D:/work/velocity-contract");

            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");


            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
