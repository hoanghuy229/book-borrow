package com.hoanghuy2209.borrowservice.query.projection;

import com.hoanghuy2209.borrowservice.command.data.BorrowRepository;
import com.hoanghuy2209.borrowservice.command.data.Borrowing;
import com.hoanghuy2209.borrowservice.query.model.BorrowResponseModel;
import com.hoanghuy2209.borrowservice.query.queries.GetAll;
import com.hoanghuy2209.borrowservice.query.queries.GetById;
import com.hoanghuy2209.borrowservice.query.queries.GetListBorrowByEmployeeId;
import com.hoanghuy2209.commonservice.model.BookResponseCommonModel;
import com.hoanghuy2209.commonservice.model.EmployeeResponseCommonModel;
import com.hoanghuy2209.commonservice.query.GetBookDetailQuery;
import com.hoanghuy2209.commonservice.query.GetUserDetailQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BorrowProjection {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private QueryGateway queryGateway;

    @QueryHandler
    public BorrowResponseModel getById(GetById getById){
        Borrowing borrowing = borrowRepository.findById(getById.getId()).orElseThrow(null);
        BorrowResponseModel borrowResponseModel = new BorrowResponseModel();
        BeanUtils.copyProperties(borrowing,borrowResponseModel);

        GetBookDetailQuery queryBook = new GetBookDetailQuery(borrowing.getBookId());
        BookResponseCommonModel bookResponseCommonModel = queryGateway.query(queryBook, ResponseTypes.instanceOf(BookResponseCommonModel.class)).join();
        borrowResponseModel.setNameBook(bookResponseCommonModel.getName());

        GetUserDetailQuery queryUser = new GetUserDetailQuery(borrowing.getEmployeeId());
        EmployeeResponseCommonModel employeeResponseCommonModel = queryGateway.query(queryUser,ResponseTypes.instanceOf(EmployeeResponseCommonModel.class)).join();
        borrowResponseModel.setNameEmployee(employeeResponseCommonModel.getLastName() + " " + employeeResponseCommonModel.getFirstName());

        return borrowResponseModel;
    }
    @QueryHandler
    public List<BorrowResponseModel> getAll(GetAll getAll){
        List<Borrowing> list = borrowRepository.findAll();
        List<BorrowResponseModel> responseModels = new ArrayList<>();
        list.forEach(i -> {
            BorrowResponseModel borrowResponseModel = new BorrowResponseModel();
            BeanUtils.copyProperties(i,borrowResponseModel);
            GetBookDetailQuery queryBook = new GetBookDetailQuery(i.getBookId());
            BookResponseCommonModel bookResponseCommonModel = queryGateway.query(queryBook, ResponseTypes.instanceOf(BookResponseCommonModel.class)).join();
            borrowResponseModel.setNameBook(bookResponseCommonModel.getName());

            GetUserDetailQuery queryUser = new GetUserDetailQuery(i.getEmployeeId());
            EmployeeResponseCommonModel employeeResponseCommonModel = queryGateway.query(queryUser,ResponseTypes.instanceOf(EmployeeResponseCommonModel.class)).join();
            borrowResponseModel.setNameEmployee(employeeResponseCommonModel.getLastName() + " " + employeeResponseCommonModel.getFirstName());

            responseModels.add(borrowResponseModel);
        });
        return responseModels;
    }

    @QueryHandler
    public List<BorrowResponseModel> getByEmployeeId(GetListBorrowByEmployeeId getListBorrowByEmployeeId){
        List<Borrowing> borrowings = borrowRepository.findByEmployeeIdAndReturnDateIsNull(getListBorrowByEmployeeId.getEmployeeId());
        List<BorrowResponseModel> borrowResponseModels = new ArrayList<>();
        borrowings.forEach(borrowing -> {
            BorrowResponseModel borrowResponseModel = new BorrowResponseModel();
            BeanUtils.copyProperties(borrowing,borrowResponseModel);

            GetBookDetailQuery queryBook = new GetBookDetailQuery(borrowing.getBookId());
            BookResponseCommonModel bookResponseCommonModel = queryGateway.query(queryBook, ResponseTypes.instanceOf(BookResponseCommonModel.class)).join();
            borrowResponseModel.setNameBook(bookResponseCommonModel.getName());

            GetUserDetailQuery queryUser = new GetUserDetailQuery(borrowing.getEmployeeId());
            EmployeeResponseCommonModel employeeResponseCommonModel = queryGateway.query(queryUser,ResponseTypes.instanceOf(EmployeeResponseCommonModel.class)).join();
            borrowResponseModel.setNameEmployee(employeeResponseCommonModel.getLastName() + " " + employeeResponseCommonModel.getFirstName());

            borrowResponseModels.add(borrowResponseModel);
        });
        return borrowResponseModels;
    }
}
