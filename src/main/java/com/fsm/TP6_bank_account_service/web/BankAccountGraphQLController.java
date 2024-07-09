package com.fsm.TP6_bank_account_service.web;

import com.fsm.TP6_bank_account_service.dto.BankAccountRequestDTO;
import com.fsm.TP6_bank_account_service.dto.BankAccountResponseDTO;
import com.fsm.TP6_bank_account_service.entities.BankAccount;
import com.fsm.TP6_bank_account_service.entities.Customer;
import com.fsm.TP6_bank_account_service.repositories.BankAccountRepository;
import com.fsm.TP6_bank_account_service.repositories.CustomerRepository;
import com.fsm.TP6_bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<BankAccount> accountsList() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Bank account with id '%s' not found", id)));
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount) {
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount) {
        return accountService.updateAccount(id,bankAccount);
    }

    @MutationMapping
    public void deleteAccount(@Argument String id) {
         bankAccountRepository.deleteById(id);
    }

    @QueryMapping
    public List<Customer> customers() {
        return customerRepository.findAll();
    }
}

//class BankAccountDTO{
//    private Double balance;
//    private String currency;
//    private String type;
//}

//record BankAccountDTO(Double balance, String type, String currency){
//
//}