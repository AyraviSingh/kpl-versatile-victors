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
public class TransactionEntity {
//    @Id
    private String accountNumber;
    private TransactionType transactionType;
    private double transactionAmount;
    private double balance;
}
