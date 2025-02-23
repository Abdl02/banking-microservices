package com.bank.event.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInitiateEvent {
    private Long customerId;
    private String accountType;
}