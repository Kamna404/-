package edu.sbs.cs.view.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResumeDTO {

    private Long id;

    @NotNull(message = "姓名不能为空")
    @Size(min = 1, max = 50, message = "姓名长度应在1至50之间")
    private String name;

    @NotNull(message = "邮箱不能为空")
    @Size(min = 5, max = 100, message = "邮箱长度应在5至100之间")
    private String email;
    
    // ...existing code: 可添加其他业务字段...

    // ...existing code: getters and setters...
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
