package org.example.ModelAbstractions;

import org.example.AccountsModel.Pair;

import java.util.List;

public interface IBankModel {
    public void AddClient(IClientModel model);
    public IClientModel GetClient(String name, String secondName);
    public IAccountController GetAccount(int Id);
    public void AddAccount(IAccountController accountController);
    public int GenerateAccountId();
    public float get_limit();
    public float get_debt();
    public float get_fixedPercent();
    public String get_name();
    public List<Pair> get_differentPercent();

    public void TimeSkip(int days);
    public void UpdateLimit(float limit);
    public void UpdateFixedPercent(float percent);
    public void UpdateDifferentPercent(List<Pair> percent);
    public ITransaction GetAndRemoveTransaction(int id);
    public void AddTransaction(ITransaction transaction);
}
