package com.hoanghuy2209.borrowservice.query.controller;

import com.hoanghuy2209.borrowservice.query.model.BorrowResponseModel;
import com.hoanghuy2209.borrowservice.query.queries.GetAll;
import com.hoanghuy2209.borrowservice.query.queries.GetById;
import com.hoanghuy2209.borrowservice.query.queries.GetListBorrowByEmployeeId;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/borrowing")
public class BorrowQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{id}")
    public BorrowResponseModel getById(@PathVariable("id") String id){
        GetById query = new GetById();
        query.setId(id);
        return queryGateway.query(query, ResponseTypes.instanceOf(BorrowResponseModel.class)).join();
    }

    @GetMapping("/by-employee/{id}")
    public List<BorrowResponseModel> getByEmployeeId(@PathVariable("id") String id){
        GetListBorrowByEmployeeId query = new GetListBorrowByEmployeeId();
        query.setEmployeeId(id);
        return queryGateway.query(query,ResponseTypes.multipleInstancesOf(BorrowResponseModel.class)).join();
    }

    @GetMapping()
    public List<BorrowResponseModel> getAll(){
        GetAll query = new GetAll();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(BorrowResponseModel.class)).join();
    }
}
