package com.sojson.document.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Document model
 */
public class Document implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Document status constants
    public static final Long STATUS_DRAFT = 0L; // Draft
    public static final Long STATUS_PUBLISHED = 1L; // Published
    public static final Long STATUS_ARCHIVED = 2L; // Archived
    
    private Long id;
    private String title; // Document title
    private String content; // Document content
    private Long directoryId; // Parent directory ID
    private Long createBy; // User ID who created the document
    private Date createTime; // Creation time
    private Long updateBy; // User ID who last updated the document
    private Date updateTime; // Last update time
    private Long status; // Document status
    private String fileUrl; // File URL if it's a file upload
    private String fileType; // File type (e.g., pdf, docx, txt)
    private Long fileSize; // File size in bytes
    private Long version; // Document version number
    
    // Constructors
    public Document() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public Long getDirectoryId() { return directoryId; }
    public void setDirectoryId(Long directoryId) { this.directoryId = directoryId; }
    
    public Long getCreateBy() { return createBy; }
    public void setCreateBy(Long createBy) { this.createBy = createBy; }
    
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    
    public Long getUpdateBy() { return updateBy; }
    public void setUpdateBy(Long updateBy) { this.updateBy = updateBy; }
    
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    
    public Long getStatus() { return status; }
    public void setStatus(Long status) { this.status = status; }
    
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}
