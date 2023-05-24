package cz.upce.bpswehosting.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadDto {

    private String fileName;
    private String path;
    private Long domainId;
    private MultipartFile data;

}
