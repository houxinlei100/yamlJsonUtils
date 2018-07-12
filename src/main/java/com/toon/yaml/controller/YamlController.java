package com.toon.yaml.controller;

import com.toon.yaml.utils.HttpUtils;
import com.toon.yaml.utils.YamlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;

@RestController
@RequestMapping("yaml")
public class YamlController {

    private static final Logger logger = LoggerFactory.getLogger(YamlController.class);
    @PostMapping(value = "/jsontoYaml")
    public String jsontoYaml(HttpServletRequest request){
        String body = "";
        try {
            body = HttpUtils.getBody(request);
            logger.info("得到请求的body内容为:" + body);
            YamlUtils.json2YamlFile(body);
        } catch (Exception e) {
            logger.error("json存为yaml文件出错，参数为: parameter =" + body);
        }
        return body;
    }

    @GetMapping("/yaml2Json")
    public String yaml2Json(String fileName){
        logger.info("YamlController-yaml2Json得到的文件名参数为：" + fileName);
        return YamlUtils.yaml2Json(fileName);
    }
    @RequestMapping("test")
    public String test(){
        return "test";
    }
}
