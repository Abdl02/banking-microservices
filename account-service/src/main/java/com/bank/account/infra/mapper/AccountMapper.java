package com.bank.account.infra.mapper;

import com.bank.account.api.dto.request.AccountRequest;
import com.bank.account.api.dto.response.AccountResponse;
import com.bank.account.repository.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {
    @Mapping(source = "customerId", target = "customerId")
    Account toEntity(AccountRequest request);

    AccountResponse toResponse(Account account);
}