package com.gaowenzheng.springboot_prj.utils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import java.sql.Types;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;

public class MybatisPlusGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入表名：");
        String table = scanner.nextLine();
        fastGenerate(table);
    }

    public static void fastGenerate(String table){
        String projectPath = System.getProperty("user.dir"); //获取项目路径
        String filePath = projectPath + "/src/main/java";  //java下的文件路径
        //加载配置文件
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("application.yml"));
        Properties properties = yamlPropertiesFactoryBean.getObject();
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        //生成文件
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("gaowenzheng") // 设置作者
                            .enableSpringdoc()
                            .disableOpenDir()
                            .outputDir(filePath); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                .packageConfig(builder -> {
                    builder.parent("com.gaowenzheng") // 设置父包名
                            .moduleName("springboot_prj") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath+"/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(table); // 设置需要生成的表名
                    builder.controllerBuilder().enableRestStyle();
                    builder.entityBuilder().enableFileOverride();//!!覆盖实体类!!
                }).templateConfig(builder -> {
                    builder.mapper("/templates/mapper.java.vm");
                    builder.controller("/templates/controller.java.vm");
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
