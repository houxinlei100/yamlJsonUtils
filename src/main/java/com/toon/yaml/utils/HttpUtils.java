package com.toon.yaml.utils;

import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

public class HttpUtils {
    public static String getBody(HttpServletRequest request) throws Exception{
        //合到编码格式
        String charset = request.getCharacterEncoding();
        //先解析协议传过来的body //用这种方法Content-Type的值必为text/json或text/plain
        return StreamUtils.copyToString(request.getInputStream(), Charset.forName(charset));
    }
}
