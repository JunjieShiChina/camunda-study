package me.shijunjiee.camundastudy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class BaseTest {

    @Test
    public void test() throws IOException {
        String filePath = "bpmn/inclusive_gateway_test.bpmn";
        InputStream inputStream = HandleBpmnTest.class.getClassLoader().getResourceAsStream(filePath);

// 创建字节数组来保存InputStream中的数据
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        // 将字节数组编码为Base64字符串
        byte[] data = outputStream.toByteArray();
        String base64String = Base64.getEncoder().encodeToString(data);

        // 输出Base64字符串
        System.out.println(base64String);

    }
}
