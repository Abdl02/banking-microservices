package com.bank.customer.clients;

import com.bank.customer.api.dto.response.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "account-service", url = "http://localhost:8082")
public interface AccountClient {
    @GetMapping("/api/accounts/customer/{customerId}")
    List<AccountResponse> getAccountsByCustomerId(@PathVariable("customerId") Long customerId);
}