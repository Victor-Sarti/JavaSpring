package br.com.sarti.JavaSpring.controllers.docs;

import br.com.sarti.JavaSpring.data.dto.v1.UploadFileResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "File Endpoint")
public interface FileControllerDocs {

    //retorna o upload
    UploadFileResponseDTO uploadFile(MultipartFile file);

    //retorna uma lista de upload
    List<UploadFileResponseDTO> uploadMultipleFiles(MultipartFile[] files);

    //retorna o download
    ResponseEntity<Resource> downloadFile(String fileName,
                                          HttpServletRequest request);
}