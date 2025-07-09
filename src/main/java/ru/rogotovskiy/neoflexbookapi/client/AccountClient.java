package ru.rogotovskiy.neoflexbookapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.rogotovskiy.neoflexbookapi.dto.AccountDto;

@FeignClient(name = "account-service", url = "http://localhost:8080")
public interface AccountClient {

    @GetMapping("/v1/user/{accountId}")
    AccountDto getAccount(@PathVariable("accountId") Long accountId);
}
