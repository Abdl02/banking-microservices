package com.bank.event.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreatedEvent {
    private Long id;
    private String name;
    private String legalId;
    private String type;
    private String address;
}