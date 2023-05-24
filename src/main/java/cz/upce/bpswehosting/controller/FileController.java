package cz.upce.bpswehosting.controller;

import cz.upce.bpswehosting.dto.DirectoryElement;
import cz.upce.bpswehosting.dto.FileUploadDto;
import cz.upce.bpswehosting.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("file")
@CrossOrigin("http://localhost:4200")
public class FileController {

    private final FileService fileService;

    @GetMapping("list")
    public DirectoryElement list(
        @RequestParam String path,
        @RequestParam Long domainId
    ) throws IOException {
        return fileService.listFiles(domainId, path);
    }

    @GetMapping("delete")
    public DirectoryElement delete(
        @RequestParam String name,
        @RequestParam String path,
        @RequestParam Long domainId
    ) throws IOException {
        return fileService.delete(domainId, path, name);
    }

    @PostMapping("upload")
    public DirectoryElement uploadFile(
        @RequestBody FileUploadDto dto
    ) throws IOException {
        return fileService.upload(
            dto.getDomainId(),
            dto.getData().getInputStream(),
            dto.getFileName(),
            dto.getPath()
        );
    }

    @PostMapping("download")
    public Resource downloadFile(
        @RequestParam String path,
        @RequestParam Long domainId
    ) throws IOException {
        OutputStream out = OutputStream.nullOutputStream();
        return fileService.download(path, domainId, out);
    }

    @GetMapping("make-dir")
    public DirectoryElement makeDir(
        @RequestParam Long domainId,
        @RequestParam String path,
        @RequestParam String name
    ) throws IOException {
        return fileService.createDirectory(domainId, path, name);
    }

    @GetMapping("remove-dir")
    public DirectoryElement removeDir(
        @RequestParam Long domainId,
        @RequestParam String path,
        @RequestParam String name
    ) throws IOException {
        return fileService.deleteDirectory(domainId, path, name);
    }
}
