package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.config.FtpConnection;
import cz.upce.bpswehosting.db.entity.Domain;
import cz.upce.bpswehosting.dto.DirectoryElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private static final String BASE_PATH = "/home/ftp_admin";
    private final FtpConnection ftpConnection;
    private final DomainService domainService;

    @Override
    public DirectoryElement upload(Long domainId, InputStream file, String fileName, String path) {
        try {
            ftpConnection.getFtpClient()
                         .changeWorkingDirectory(
                             BASE_PATH + domainService.getOne(domainId).getBasePath() + path);
            ftpConnection.getFtpClient().storeFile(fileName, file);
            return listFiles(domainId, path);
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public @ResponseBody Resource download(String path, String fileName, Long domainId, OutputStream out) {
        InputStream in = InputStream.nullInputStream();
        try {
            ftpConnection.getFtpClient()
                         .changeWorkingDirectory(
                             BASE_PATH + domainService.getOne(domainId).getBasePath() + path
                         );

            ftpConnection.getFtpClient().retrieveFile(fileName, out);
            IOUtils.copy(in, out);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        return new InputStreamResource(in);
    }

    @Override
    public DirectoryElement delete(Long domainId, String path, String name) {
        try {
            Domain domain = domainService.getOne(domainId);
            ftpConnection.getFtpClient().changeWorkingDirectory(BASE_PATH + domain.getBasePath() + path);
            ftpConnection.getFtpClient().deleteFile(name);
            return listFiles(domainId, path);
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public DirectoryElement createDirectory(Long domainId, String path, String name) {
        try {
            Domain domain = domainService.getOne(domainId);
            ftpConnection.getFtpClient().changeWorkingDirectory(BASE_PATH + domain.getBasePath() + path);
            ftpConnection.getFtpClient().makeDirectory(name);
            return listFiles(domainId, path);
        } catch (IOException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public DirectoryElement deleteDirectory(Long domainId, String path, String name) {
        try {
            Domain domain = domainService.getOne(domainId);
            ftpConnection.getFtpClient().changeWorkingDirectory(BASE_PATH + domain.getBasePath() + path);
            ftpConnection.getFtpClient().removeDirectory(name);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return listFiles(domainId, path);
    }

    @Override
    public DirectoryElement listFiles(Long domainId, String path) {
        String[] folders = path.split("/");
        String folder = folders[folders.length - 1];
        DirectoryElement directoryElement = new DirectoryElement(folder, "folder", new ArrayList<>());
        Domain domain = domainService.getOne(domainId);
        try {
            ftpConnection.getFtpClient().changeWorkingDirectory(BASE_PATH + domain.getBasePath() + path);
            Arrays.stream(ftpConnection.getFtpClient().listDirectories())
                  .forEach(
                      dir -> directoryElement.getChildren().add(new DirectoryElement(dir.getName(), "folder"))
                  );
            Arrays.stream(ftpConnection.getFtpClient().listFiles())
                  .forEach(ftpFile -> directoryElement.getChildren()
                                                      .add(new DirectoryElement(ftpFile.getName(), "file"))
                  );
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return directoryElement;
    }

    private String resolveType(int type) {
        return type == 1 ? "folder" : "file";
    }

}
