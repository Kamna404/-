package edu.sbs.cs.view.service;

import edu.sbs.cs.view.dto.ResumeDTO;
import edu.sbs.cs.view.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;
    
    // 模拟文件存储（内存中存储文件数据）
    private Map<String, byte[]> fileStorage = new HashMap<>();

    public Collection<ResumeDTO> getAllResumes() {
        return resumeRepository.findAll();
    }

    public ResumeDTO getResumeById(Long id) {
        ResumeDTO resume = resumeRepository.findById(id);
        if (resume == null) {
            throw new RuntimeException("数据不存在");
        }
        return resume;
    }

    public ResumeDTO createResume(ResumeDTO resume) {
        return resumeRepository.save(resume);
    }

    public ResumeDTO updateResume(Long id, ResumeDTO resume) {
        ResumeDTO exist = resumeRepository.findById(id);
        if (exist == null) {
            throw new RuntimeException("数据不存在");
        }
        resume.setId(id);
        return resumeRepository.save(resume);
    }

    public void deleteResume(Long id) {
        ResumeDTO exist = resumeRepository.findById(id);
        if (exist == null) {
            throw new RuntimeException("数据不存在");
        }
        resumeRepository.delete(id);
    }

    public String uploadFile(MultipartFile file) throws IOException {
        // 限制文件大小（5MB）
        long maxSize = 5 * 1024 * 1024; // 5MB in bytes
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("文件大小超过限制，最大允许5MB");
        }
        
        // 限制文件类型
        String filename = file.getOriginalFilename();
        if (filename != null) {
            String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            String[] allowedExtensions = {"jpg", "jpeg", "png", "pdf", "doc", "docx"};
            boolean isAllowed = false;
            for (String ext : allowedExtensions) {
                if (ext.equals(extension)) {
                    isAllowed = true;
                    break;
                }
            }
            if (!isAllowed) {
                throw new IllegalArgumentException("文件类型不支持，仅允许jpg, jpeg, png, pdf, doc, docx格式");
            }
        } else {
            throw new IllegalArgumentException("文件名不能为空");
        }
        
        fileStorage.put(filename, file.getBytes());
        return filename;
    }

    public byte[] downloadFile(String fileName) {
        byte[] fileData = fileStorage.get(fileName);
        if (fileData == null) {
            throw new RuntimeException("文件不存在");
        }
        return fileData;
    }
}
