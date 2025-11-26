package com.sojson.common.mapper;

import com.sojson.common.model.Department;
import java.util.List;

public interface DepartmentMapper {
    Department selectByPrimaryKey(Long id);
    List<Department> selectAll();
    List<Department> selectByParentId(Long parentId);
    int insert(Department department);
    int updateByPrimaryKey(Department department);
    int deleteByPrimaryKey(Long id);
}
