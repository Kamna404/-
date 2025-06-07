package edu.sbs.cs.view.controller;

import edu.sbs.cs.view.dto.ResponseWrapper;
import edu.sbs.cs.view.dto.ResumeDTO;
import edu.sbs.cs.view.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/resumes")
@Validated
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping
    public ResponseWrapper<Collection<ResumeDTO>> getResumes() {
        Collection<ResumeDTO> resumes = resumeService.getAllResumes();
        return ResponseWrapper.success(resumes);
    }

    @GetMapping("/{id}")
    public ResponseWrapper<ResumeDTO> getResume(@PathVariable Long id) {
        ResumeDTO resume = resumeService.getResumeById(id);
        return ResponseWrapper.success(resume);
    }

    @PostMapping
    public ResponseWrapper<ResumeDTO> createResume(@Valid @RequestBody ResumeDTO resumeDTO) {
        ResumeDTO created = resumeService.createResume(resumeDTO);
        return ResponseWrapper.success(created);
    }

    @PutMapping("/{id}")
    public ResponseWrapper<ResumeDTO> updateResume(@PathVariable Long id, @Valid @RequestBody ResumeDTO resumeDTO) {
        ResumeDTO updated = resumeService.updateResume(id, resumeDTO);
        return ResponseWrapper.success(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseWrapper<String> deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseWrapper.success("删除成功");
    }
    
    @PostMapping("/upload")
    public ResponseWrapper<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            String fileName = resumeService.uploadFile(file);
            return ResponseWrapper.success("上传成功，文件名：" + fileName);
        } catch (IllegalArgumentException e) {
            return ResponseWrapper.error(400, e.getMessage());
        }
    }
    
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("fileName") String fileName) {
        byte[] data = resumeService.downloadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }
}
