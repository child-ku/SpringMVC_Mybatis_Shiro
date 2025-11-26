package com.sojson.time.model;

import java.util.Date;
import java.math.BigDecimal;

public class TimeModificationRequest {
    private Long id;
    private Long timeRecordId;
    private Long userId;
    private BigDecimal newHours;
    private String newDescription;
    private String reason;
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
    public Long getTimeRecordId() {
        return timeRecordId;
    }
    public void setTimeRecordId(Long timeRecordId) {
        this.timeRecordId = timeRecordId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public BigDecimal getNewHours() {
        return newHours;
    }
    public void setNewHours(BigDecimal newHours) {
        this.newHours = newHours;
    }
    public String getNewDescription() {
        return newDescription;
    }
    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
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