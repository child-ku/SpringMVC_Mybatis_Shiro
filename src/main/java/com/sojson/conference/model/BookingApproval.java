package com.sojson.conference.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Booking Approval Model Class
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
public class BookingApproval implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Approval status constants
    public static final Integer STATUS_PENDING_APPROVAL = 1;
    public static final Integer STATUS_APPROVED = 2;
    public static final Integer STATUS_REJECTED = 3;
    
    private Long id;
    /**预订ID*/
    private Long bookingId;
    /**审批人ID*/
    private Long approverId;
    /**审批状态：1-待审批，2-已批准，3-已拒绝*/
    private Integer approvalStatus;
    /**审批意见*/
    private String comment;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
    
    // Constructors
    public BookingApproval() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    
    public Long getApproverId() { return approverId; }
    public void setApproverId(Long approverId) { this.approverId = approverId; }
    
    public Integer getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(Integer approvalStatus) { this.approvalStatus = approvalStatus; }
    
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    
    @Override
    public String toString() {
        return "BookingApproval{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", approverId=" + approverId +
                ", approvalStatus=" + approvalStatus +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}