package org.example.ModelAbstractions;

import org.example.AccountsModel.Pair;
import org.example.Core.MainBankResult;

import java.util.List;

public interface IMainBankController {
public void RegisterNewBank(String name, float limit, float debt, float fixedPercent, List<Pair> differentPercent);
public MainBankResult TransferMoneyBetweenBanks(String fromBank, int fromId, String toBank, int toId, float amount);
public MainBankResult UndoTransaction(int id, String fromBank, String toBank);
public IBankController GetBank(String name);

}
