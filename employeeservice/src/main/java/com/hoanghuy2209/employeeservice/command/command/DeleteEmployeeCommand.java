package com.hoanghuy2209.employeeservice.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class DeleteEmployeeCommand {
    @TargetAggregateIdentifier
    private String employeeId;

}
