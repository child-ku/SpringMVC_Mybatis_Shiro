package com.sojson.conference.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Conference Room Model Class
 * 
 * @author zhou-baicheng
 * @email i@itboy.net
 * @version 1.0
 */
public class ConferenceRoom implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Status constants
    public static final Integer STATUS_AVAILABLE = 1;
    public static final Integer STATUS_UNAVAILABLE = 2;
    
    private Long id;
    /**会议室名称*/
    private String name;
    /**容纳人数*/
    private Integer capacity;
    /**会议室位置*/
    private String location;
    /**会议室设备*/
    private String equipment;
    /**状态：1-可用，2-不可用*/
    private Integer status;
    /**描述*/
    private String description;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date createTime;
    /**更新人*/
    private String updater;
    /**更新时间*/
    private Date updateTime;
    
    // Constructors
    public ConferenceRoom() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }
    
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCreator() { return creator; }
    public void setCreator(String creator) { this.creator = creator; }
    
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    
    public String getUpdater() { return updater; }
    public void setUpdater(String updater) { this.updater = updater; }
    
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    
    @Override
    public String toString() {
        return "ConferenceRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", location='" + location + '\'' +
                ", equipment='" + equipment + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updater='" + updater + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}