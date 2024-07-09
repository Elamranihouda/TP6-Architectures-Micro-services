package com.fsm.TP6_bank_account_service.service;

import com.fsm.TP6_bank_account_service.dto.BankAccountRequestDTO;
import com.fsm.TP6_bank_account_service.dto.BankAccountResponseDTO;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
