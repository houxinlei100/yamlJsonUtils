package com.toon.yaml.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class YamlUtils {
    public static final Logger logger = LoggerFactory.getLogger(YamlUtils.class);
    public static void json2YamlFile(String jsonString){
        JsonNode jsonNodeTree = null;
        // save it as YAML
        String jsonAsYaml = null;
        try {
            jsonNodeTree = new ObjectMapper().readTree(jsonString);
            jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
            String yamlString = jsonAsYaml.substring(4);
            //写入文件
            File directory = new File("");// 参数为空
            String path = "\\src\\main\\resources\\";
            String courseFile = directory.getCanonicalPath() + path;
            FileWriter out = new FileWriter(courseFile +"/ccc.yaml", false);
            //往文件写入
            out.write(yamlString);
            //刷新IO内存流
            out.flush();
            //关闭
            out.close();
        } catch (Exception e) {
            logger.error("YamlUtils,json转yaml,并存yaml文件的时候出错，参数为: jsonStrin =" + jsonString);
        }
    }

    public static String yaml2Json(String fileName){
        Gson gs = new Gson();
        Map<String, Object> loaded = null;
        try {
            File directory = new File("");// 参数为空
            String path = "\\src\\main\\resources\\";
            String courseFile =  directory.getCanonicalPath() + path;
            FileInputStream fis = new FileInputStream(courseFile + fileName+".yaml");
            Yaml yaml = new Yaml();
            loaded = (Map<String, Object>) yaml.load(fis);
        } catch (Exception e) {
            logger.error("读取yaml文件或yaml转json字符串出错，文件名为:"+fileName);
        }
        return gs.toJson(loaded);
    }
}
