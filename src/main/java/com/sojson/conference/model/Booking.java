package com.sojson.conference.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Booking Model Class
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Status constants
    public static final Integer STATUS_PENDING_APPROVAL = 1;
    public static final Integer STATUS_APPROVED = 2;
    public static final Integer STATUS_REJECTED = 3;
    public static final Integer STATUS_COMPLETED = 4;
    
    private Long id;
    /**会议室ID*/
    private Long roomId;
    /**预订人ID*/
    private Long userId;
    /**开始时间*/
    private Date startTime;
    /**结束时间*/
    private Date endTime;
    /**状态：1-待审批，2-已批准，3-已拒绝，4-已完成*/
    private Integer status;
    /**预订原因*/
    private String reason;
    /**联系方式*/
    private String contact;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
    
    // Constructors
    public Booking() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }
    
    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }
    
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", reason='" + reason + '\'' +
                ", contact='" + contact + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}