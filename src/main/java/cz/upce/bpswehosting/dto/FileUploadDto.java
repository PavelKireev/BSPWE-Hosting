package cz.upce.bpswehosting.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadDto {

    private Long domainId;
    private MultipartFile file;
    private String path;
    private String fileName;

}
