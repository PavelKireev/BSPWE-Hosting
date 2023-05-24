package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.dto.DirectoryElement;
import org.springframework.core.io.InputStreamResource;

import java.io.*;

public interface FileService {

    DirectoryElement upload(Long domainId, InputStream file, String fileName, String path) throws IOException;
    InputStreamResource download(String path, Long domainId, OutputStream out);
    DirectoryElement delete(Long domainId, String path, String name) throws IOException;
    DirectoryElement createDirectory(Long domainId, String path, String name) throws IOException;
    DirectoryElement deleteDirectory(Long domainId, String path, String name) throws IOException;
    DirectoryElement listFiles(Long domainId, String path) throws IOException;
}
