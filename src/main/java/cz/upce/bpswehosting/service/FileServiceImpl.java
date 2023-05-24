package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.config.FtpConnection;
import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.dto.DirectoryElement;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FtpConnection ftpConnection;
    private final DomainService domainService;

    @Override
    public DirectoryElement upload(Long domainId, InputStream file, String fileName, String path) throws IOException {
        ftpConnection.getFtpClient().changeWorkingDirectory(domainService.getOne(domainId)
                                                                                   .getBasePath() + path);
        ftpConnection.getFtpClient().storeFile(fileName, file);
        return listFiles(domainId, path);
    }

    @Override
    public InputStreamResource download(String path, OutputStream out) throws IOException {
        InputStream in = InputStream.nullInputStream();
        ftpConnection.getFtpClient().retrieveFile(path, out);
        IOUtils.copy(in, out);
        return new InputStreamResource(in);
    }

    @Override
    public DirectoryElement delete(Long domainId, String path, String name) throws IOException {
        ftpConnection.getFtpClient().changeWorkingDirectory('/' + domainService.getOne(domainId)
                                                                                         .getBasePath() + path);
        ftpConnection.getFtpClient().deleteFile(path);
        return listFiles(domainId, path);
    }

    @Override
    public DirectoryElement createDirectory(Long domainId, String path, String name) {
        try {
            ftpConnection.getFtpClient().makeDirectory(path);
            return listFiles(domainId, path);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public DirectoryElement deleteDirectory(Long domainId, String path, String name) throws IOException {
        ftpConnection.getFtpClient().listDirectories();
        ftpConnection.getFtpClient().removeDirectory(name);
        return listFiles(domainId, path);
    }

    @Override
    public DirectoryElement listFiles(Long domainId, String path) throws IOException {
        String[] folders = path.split("/");
        String folder = folders[folders.length - 1];
        DirectoryElement directoryElement = new DirectoryElement(folder, "folder", new ArrayList<>());
        Domain domain = domainService.getOne(domainId);
        Arrays.stream(ftpConnection.getFtpClient().listFiles(domain.getBasePath() + path))
              .forEach(ftpFile -> directoryElement.getChildren().add(new DirectoryElement(
                    ftpFile.getName(), resolveType(ftpFile.getType())
              ))
            );
        return directoryElement;
    }

    private String resolveType(int type) {
        return type == 1 ? "folder" : "file";
    }

}
