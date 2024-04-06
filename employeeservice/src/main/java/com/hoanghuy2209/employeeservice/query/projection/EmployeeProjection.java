package com.hoanghuy2209.employeeservice.query.projection;

import com.hoanghuy2209.commonservice.model.EmployeeResponseCommonModel;
import com.hoanghuy2209.commonservice.query.GetUserDetailQuery;
import com.hoanghuy2209.employeeservice.command.data.Employee;
import com.hoanghuy2209.employeeservice.command.data.EmployeeRepository;
import com.hoanghuy2209.employeeservice.query.model.EmployeeResponseModel;
import com.hoanghuy2209.employeeservice.query.queries.GetAllQuery;
import com.hoanghuy2209.employeeservice.query.queries.GetByIdQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeProjection {
    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryHandler
    public EmployeeResponseModel handle(GetByIdQuery getByIdQuery){
        EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
        Employee employee = employeeRepository.findById(getByIdQuery.getEmployeeId()).orElseThrow(null);
        BeanUtils.copyProperties(employee,employeeResponseModel);
        return employeeResponseModel;
    }
    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllQuery getAllQuery){
        List<EmployeeResponseModel> employeeResponseModels = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(employee -> {
            EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee,employeeResponseModel);
            employeeResponseModels.add(employeeResponseModel);
        });
        return employeeResponseModels;
    }

    @QueryHandler
    public EmployeeResponseCommonModel handle(GetUserDetailQuery getUserDetailQuery){
        EmployeeResponseCommonModel employeeResponseModel = new EmployeeResponseCommonModel();
        Employee employee = employeeRepository.findById(getUserDetailQuery.getEmployeeId()).orElseThrow(null);
        BeanUtils.copyProperties(employee,employeeResponseModel);
        return employeeResponseModel;
    }

}
