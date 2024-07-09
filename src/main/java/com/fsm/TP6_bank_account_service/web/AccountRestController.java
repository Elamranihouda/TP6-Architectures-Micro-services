package com.fsm.TP6_bank_account_service.web;

import com.fsm.TP6_bank_account_service.dto.BankAccountRequestDTO;
import com.fsm.TP6_bank_account_service.dto.BankAccountResponseDTO;
import com.fsm.TP6_bank_account_service.entities.BankAccount;
import com.fsm.TP6_bank_account_service.mappers.AccountMapper;
import com.fsm.TP6_bank_account_service.repositories.BankAccountRepository;
import com.fsm.TP6_bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AccountRestController {

    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }


    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Bank account with id '%s' not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount updat(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if (account.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if (account.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        if (account.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if (account.getType()!=null) account.setType(bankAccount.getType());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}
