package com.sojson.time.model;

import java.util.Date;
import java.math.BigDecimal;

public class TimeRecord {
    private Long id;
    private Long userId;
    private Long projectId;
    private Date workDate;
    private BigDecimal hours;
    private String description;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Long approverId;
    private Date approveTime;
    private String approveComment;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public Date getWorkDate() {
        return workDate;
    }
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }
    public BigDecimal getHours() {
        return hours;
    }
    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Long getApproverId() {
        return approverId;
    }
    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }
    public Date getApproveTime() {
        return approveTime;
    }
    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }
    public String getApproveComment() {
        return approveComment;
    }
    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment;
    }
}