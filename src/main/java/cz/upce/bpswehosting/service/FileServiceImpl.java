package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.config.FtpConnection;
import lombok.RequiredArgsConstructor;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FtpConnection ftpConnection;
    private final DomainService domainService;

    @Override
    public void upload(Long domainId, InputStream file, String fileName, String path) throws IOException {
        ftpConnection.getFtpClient()
                     .changeWorkingDirectory(domainService.getOne(domainId).getBasePath() + path);
        ftpConnection.getFtpClient().storeFile(fileName, file);
    }

    @Override
    public void download(String path, OutputStream out) throws IOException {
        ftpConnection.getFtpClient().retrieveFile(path, out);
    }

    @Override
    public void delete(Long domainId, String path, String name) throws IOException {
        ftpConnection.getFtpClient().changeWorkingDirectory('/' + domainService.getOne(domainId).getBasePath() + path);
        ftpConnection.getFtpClient().deleteFile(path);
    }

    @Override
    public void createDirectory(Long domainId, String path, String name) throws IOException {
        ftpConnection.getFtpClient().makeDirectory(path);
    }

    @Override
    public void deleteDirectory(Long domainId, String path, String name) throws IOException {
        ftpConnection.getFtpClient().listDirectories();
        ftpConnection.getFtpClient().removeDirectory(name);
    }
}
