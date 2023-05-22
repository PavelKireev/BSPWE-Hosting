package cz.upce.bpswehosting.controller;

import cz.upce.bpswehosting.dto.FileUploadDto;
import cz.upce.bpswehosting.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("file")
@CrossOrigin("http://localhost:4200")
public class FileController {

    private final FileService fileService;

    @PostMapping("upload")
    public void uploadFile(
        @RequestBody FileUploadDto dto
    ) throws IOException {
        fileService.upload(
            dto.getDomainId(),
            dto.getFile().getInputStream(),
            dto.getFileName(),
            dto.getPath()
        );
    }

    @PostMapping("download")
    public void downloadFile() {
//        fileService.download();
    }

    @GetMapping("make-dir")
    public void makeDir(
        @RequestParam Long domainId,
        @RequestParam String path,
        @RequestParam String name
    ) throws IOException {
        fileService.createDirectory(domainId, path, name);
    }

    @GetMapping("remove-dir")
    public void removeDir(
        @RequestParam Long domainId,
        @RequestParam String path,
        @RequestParam String name
    ) throws IOException {
        fileService.deleteDirectory(domainId, path, name);
    }
}
