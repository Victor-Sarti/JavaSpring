package br.com.sarti.JavaSpring.controllers;

import br.com.sarti.JavaSpring.controllers.docs.FileControllerDocs;
import br.com.sarti.JavaSpring.data.dto.v1.UploadFileResponseDTO;
import br.com.sarti.JavaSpring.services.FileStorageService;
import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/file/v1")
public class FileController implements FileControllerDocs {

    private static final Logger logger = LoggerFactory.getLogger(FileControllerDocs.class);

    @Autowired
    private FileStorageService service;

    @PostMapping("/uploadFile")
    @Override
    public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
        var fileName = service.storeFile(file);

        // http://localhost:8080/api/file/v1/downloadFile/filename.docx
        var fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/v1/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @Override
    public List<UploadFileResponseDTO> uploadMultipleFiles(MultipartFile[] files) {
        return List.of();
    }

    @Override
    public ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request) {
        return null;
    }
}
