package cz.upce.bpswehosting.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FTPClient ftpClient;
    private final DomainService domainService;

    @Override
    public void upload(Long domainId, InputStream file, String fileName, String path) throws IOException {
        ftpClient.changeWorkingDirectory(domainService.getOne(domainId).getBasePath() + path);
        ftpClient.storeFile(fileName, file);
    }

    @Override
    public void download(String path, OutputStream out) throws IOException {
        ftpClient.retrieveFile(path, out);
    }

    @Override
    public void delete(Long domainId, String path, String name) throws IOException {
        ftpClient.changeWorkingDirectory('/' + domainService.getOne(domainId).getBasePath() + path);
        ftpClient.deleteFile(path);
    }

    @Override
    public void createDirectory(Long domainId, String path, String name) throws IOException {
        ftpClient.makeDirectory(path);
    }

    @Override
    public void deleteDirectory(Long domainId, String path, String name) throws IOException {
        ftpClient.listDirectories();
        ftpClient.removeDirectory(name);
    }
}
