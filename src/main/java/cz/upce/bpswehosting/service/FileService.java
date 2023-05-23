package cz.upce.bpswehosting.service;

import cz.upce.bpswehosting.dto.DirectoryElement;

import java.io.*;
import java.util.List;

public interface FileService {

    boolean upload(Long domainId, InputStream file, String fileName, String path) throws IOException;
    boolean download(String path, OutputStream out) throws IOException;
    List<DirectoryElement> delete(Long domainId, String path, String name) throws IOException;
    List<DirectoryElement> createDirectory(Long domainId, String path, String name) throws IOException;
    List<DirectoryElement> deleteDirectory(Long domainId, String path, String name) throws IOException;
    List<DirectoryElement> listFiles(Long domainId, String path) throws IOException;
}
