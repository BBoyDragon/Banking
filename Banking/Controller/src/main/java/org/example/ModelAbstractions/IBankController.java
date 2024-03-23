package org.example.ModelAbstractions;

import org.example.AccountsModel.Pair;
import org.example.BanksModel.BankOperationResult;

import java.util.List;

public interface IBankController {
    public void CreateDebetAccount(String name, String secondName);
    public void CreateDepositAccount(String name, String secondName, int term);
    public void CreateCreditAccount(String name, String secondName);
    public void CrateClient(IClientModelBuilder clientModelBuilder);

    public BankOperationResult AddMoneyOnAccount(int id, float amount);
    public BankOperationResult RemoveMoneyFromAccount(int id, float amount);

    public String get_name();

    public BankOperationResult TransferMoneyBetweenAccounts(int fromId, int toId, float amount);
    public void TimeSkip(int dayAmount);

    public void UpdateLimit(float limit);
    public void UpdateFixedPercent(float percent);
    public void UpdateDifferentPercent(List<Pair> percent);

    public void RegisterTransaction(ITransaction transaction);
    public void UndoTransaction(int transactionId);

    public IAccountController GetAccount(int id);
}
