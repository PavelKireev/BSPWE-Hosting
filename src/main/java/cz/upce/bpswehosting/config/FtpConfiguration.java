package cz.upce.bpswehosting.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class FtpConfiguration {

    @Value("${ftp.server}")
    private String server;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.username}")
    private String user;
    @Value("${ftp.password}")
    private String password;

    @Getter
    FTPClient ftpClient;

    @PostConstruct
    public void init() throws IOException {
        ftpClient = new FTPClient();
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftpClient.connect(server, port);
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }

        ftpClient.login(user, password);
    }

    @PreDestroy
    public void destroy() throws IOException {
        ftpClient.disconnect();
    }

}
