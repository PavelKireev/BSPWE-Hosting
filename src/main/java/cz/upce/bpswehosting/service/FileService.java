package cz.upce.bpswehosting.service;

public interface FileService {

    void upload(Long domainId, byte[] data);
    void delete(Long domainId, byte[] data);
    void createDirectory(Long domainId, String path);
    void deleteDirectory(Long domainId, String path);

}
