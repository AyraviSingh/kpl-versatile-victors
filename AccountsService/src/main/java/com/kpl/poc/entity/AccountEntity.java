package com.kpl.poc.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountEntity {
//    @Id
    private String userName;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private String accountNumber;
    private double balance;
}
