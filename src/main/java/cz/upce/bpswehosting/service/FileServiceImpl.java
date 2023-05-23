package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.config.FtpConnection;
import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.dto.DirectoryElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FtpConnection ftpConnection;
    private final DomainService domainService;

    @Override
    public boolean upload(Long domainId, InputStream file, String fileName, String path) throws IOException {
        ftpConnection.getFtpClient()
                     .changeWorkingDirectory(domainService.getOne(domainId).getBasePath() + path);
        return ftpConnection.getFtpClient().storeFile(fileName, file);
    }

    @Override
    public boolean download(String path, OutputStream out) throws IOException {
        return ftpConnection.getFtpClient().retrieveFile(path, out);
    }

    @Override
    public List<DirectoryElement> delete(Long domainId, String path, String name) throws IOException {
        ftpConnection.getFtpClient().changeWorkingDirectory('/' + domainService.getOne(domainId).getBasePath() + path);
        ftpConnection.getFtpClient().deleteFile(path);
        return listFiles(domainId, path);
    }

    @Override
    public List<DirectoryElement> createDirectory(Long domainId, String path, String name) throws IOException {
        ftpConnection.getFtpClient().makeDirectory(path);
        return listFiles(domainId, path);
    }

    @Override
    public List<DirectoryElement> deleteDirectory(Long domainId, String path, String name) throws IOException {
        ftpConnection.getFtpClient().listDirectories();
        ftpConnection.getFtpClient().removeDirectory(name);
        return listFiles(domainId, path);
    }

    @Override
    public List<DirectoryElement> listFiles(Long domainId, String path) throws IOException {
        Domain domain = domainService.getOne(domainId);
        return Arrays.stream(ftpConnection.getFtpClient().listFiles(domain.getBasePath() + path))
                     .map(ftpFile -> new DirectoryElement(ftpFile.getName(), ftpFile.getType()))
                     .collect(Collectors.toList());
    }

}
