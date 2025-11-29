package com.sojson.workorder.model;

import java.io.Serializable;

/**
 * 工单状态实体类
 * 
 * @author AI Assistant
 * @version 1.0
 */
public class WorkOrderStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String description;
    
    public WorkOrderStatus() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}