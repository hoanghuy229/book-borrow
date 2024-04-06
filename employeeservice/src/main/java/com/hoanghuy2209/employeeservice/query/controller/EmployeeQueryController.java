package com.hoanghuy2209.employeeservice.query.controller;

import com.hoanghuy2209.employeeservice.query.model.EmployeeResponseModel;
import com.hoanghuy2209.employeeservice.query.queries.GetAllQuery;
import com.hoanghuy2209.employeeservice.query.queries.GetByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping()
    public List<EmployeeResponseModel> getAllEmployee(){
        GetAllQuery query = new GetAllQuery();
        List<EmployeeResponseModel> employeeResponseModels = queryGateway.query(query, ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
        return employeeResponseModels;
    }
    @GetMapping("/{id}")
    public EmployeeResponseModel getById(@PathVariable("id") String id){
        GetByIdQuery query = new GetByIdQuery();
        query.setEmployeeId(id);
        EmployeeResponseModel employeeResponseModel = queryGateway.query(query,ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
        return employeeResponseModel;
    }
}
