package com.hjznb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class SimpleFlowConfig {

    /**
     * Transformer转换器，inputChannel为输入管道，outputChannel为转换后输出管道
     *
     * @return
     */
    @Profile("javaconfig")
    @Bean
    @Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> upperCaseTransformer() {
        return text -> text.toUpperCase();
    }

    /**
     * 声明文件写入器，@ServiceActivator代表是一个服务激活器
     *
     * @return
     */
    @Profile("javaconfig")
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File("D:\\tacoo\\simple-flow\\src\\main\\java\\com\\hjznb"));
        //设置无答复通道
        handler.setExpectReply(false);
        //设置为追加
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }

    /**
     * 声明通道
     *
     * @return
     */
    @Bean
    @Profile("javaconfig")
    public MessageChannel textInChannel() {
        return new DirectChannel();
    }

    @Bean
    @Profile("javaconfig")
    public MessageChannel fileWriterChannel() {
        return new DirectChannel();
    }

    /**
     * dsl配置方式
     * @return
     */
    @Profile("javadsl")
    @Bean
    public IntegrationFlow fileWriterFlow() {
        return IntegrationFlows
                .from(MessageChannels.direct("textInChannel"))
                .<String, String>transform(t -> t.toUpperCase())
                .handle(Files.outboundAdapter(new File("D:\\tacoo\\simple-flow\\src\\main\\java\\com\\hjznb"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true))
                .get();

    }
}
