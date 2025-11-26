package com.sojson.common.mapper;

import com.sojson.common.model.Employee;
import java.util.List;

public interface EmployeeMapper {
    Employee selectByPrimaryKey(Long id);
    List<Employee> selectAll();
    List<Employee> selectByDepartmentId(Long departmentId);
    Employee selectByUserId(Long userId);
    int insert(Employee employee);
    int updateByPrimaryKey(Employee employee);
    int deleteByPrimaryKey(Long id);
}
