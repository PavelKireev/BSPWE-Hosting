package cz.upce.bpswehosting.service;

import java.io.*;

public interface FileService {

    void upload(Long domainId, InputStream file, String fileName, String path) throws IOException;
    void download(String path, OutputStream out) throws IOException;
    void delete(Long domainId, String path, String name) throws IOException;
    void createDirectory(Long domainId, String path, String name) throws IOException;
    void deleteDirectory(Long domainId, String path, String name) throws IOException;

}
