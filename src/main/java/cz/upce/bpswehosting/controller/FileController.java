package cz.upce.bpswehosting.controller;

import cz.upce.bpswehosting.dto.DirectoryElement;
import cz.upce.bpswehosting.dto.FileUploadDto;
import cz.upce.bpswehosting.service.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
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
    public List<DirectoryElement> list(
        @RequestParam String path,
        @RequestParam Long domainId
    ) throws IOException {
        return fileService.listFiles(domainId, path);
    }

    @PostMapping("upload")
    public ResponseEntity<Void> uploadFile(
        @RequestBody FileUploadDto dto
    ) throws IOException {
        boolean success = fileService.upload(
            dto.getDomainId(),
            dto.getFile().getInputStream(),
            dto.getFileName(),
            dto.getPath()
        );

        return success ? ResponseEntity.ok().build()
                       : ResponseEntity.badRequest().build();
    }

    @PostMapping("download")
    public ResponseEntity<Resource> downloadFile(
        @RequestParam String path
    ) throws IOException {
        OutputStream out = new ByteArrayOutputStream();
        fileService.download(path, out);
//        Resource resource = new ByteArrayResource(out);
        return null;
    }

    @GetMapping("make-dir")
    public List<DirectoryElement> makeDir(
        @RequestParam Long domainId,
        @RequestParam String path,
        @RequestParam String name
    ) throws IOException {
        return fileService.createDirectory(domainId, path, name);
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
