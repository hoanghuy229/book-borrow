package com.hoanghuy2209.employeeservice.command.aggregate;

import com.hoanghuy2209.employeeservice.command.command.CreateEmployeeCommand;
import com.hoanghuy2209.employeeservice.command.command.DeleteEmployeeCommand;
import com.hoanghuy2209.employeeservice.command.command.UpdateEmployeeCommand;
import com.hoanghuy2209.employeeservice.command.event.CreateEmployeeEvent;
import com.hoanghuy2209.employeeservice.command.event.DeleteEmployeeEvent;
import com.hoanghuy2209.employeeservice.command.event.UpdateEmployeeEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
@NoArgsConstructor
public class EmployeeAggregate {
    @AggregateIdentifier
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command){
        CreateEmployeeEvent event = new CreateEmployeeEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(UpdateEmployeeCommand command){
        UpdateEmployeeEvent event = new UpdateEmployeeEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(DeleteEmployeeCommand command){
        DeleteEmployeeEvent event = new DeleteEmployeeEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }


    @EventSourcingHandler
    public void on(CreateEmployeeEvent event){
        this.employeeId = event.getEmployeeId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }
    @EventSourcingHandler
    public void on(UpdateEmployeeEvent event){
        this.employeeId = event.getEmployeeId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }
    @EventSourcingHandler
    public void on(DeleteEmployeeEvent event){
        this.employeeId = event.getEmployeeId();
    }
}
