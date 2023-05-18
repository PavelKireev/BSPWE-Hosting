package cz.upce.bpswehosting.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FTPClient ftpClient;

    @Override
    public void upload(Long domainId, byte[] data) {

    }

    @Override
    public void delete(Long domainId, byte[] data) {

    }

    @Override
    public void createDirectory(Long domainId, String path) {

    }

    @Override
    public void deleteDirectory(Long domainId, String path) {

    }
}
