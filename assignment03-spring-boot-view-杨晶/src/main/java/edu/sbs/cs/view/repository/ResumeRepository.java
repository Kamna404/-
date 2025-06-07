package edu.sbs.cs.view.repository;

import edu.sbs.cs.view.dto.ResumeDTO;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class ResumeRepository {
    private ConcurrentHashMap<Long, ResumeDTO> resumeStorage = new ConcurrentHashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    public ResumeDTO save(ResumeDTO resume) {
        if (resume.getId() == null) {
            resume.setId(idGenerator.getAndIncrement());
        }
        resumeStorage.put(resume.getId(), resume);
        return resume;
    }

    public ResumeDTO findById(Long id) {
        return resumeStorage.get(id);
    }

    public Collection<ResumeDTO> findAll() {
        return resumeStorage.values();
    }

    public void delete(Long id) {
        resumeStorage.remove(id);
    }
}
