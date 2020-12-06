package com.hjznb;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 消息网关接口
 */
@MessagingGateway(defaultRequestChannel = "textInChannel")
public interface FileWriterGateway {
    /**
     * 会在运行时生成该接口的实现，调用该方法将数据写入文件
     *
     * @param filename 要写入的文件名，传递给filename的值要包含在消息头信息中，而不是载荷中
     * @param data     写入的数据
     */
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, String data);
}
