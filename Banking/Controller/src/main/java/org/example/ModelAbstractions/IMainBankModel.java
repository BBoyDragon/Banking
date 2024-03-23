package org.example.ModelAbstractions;

public interface IMainBankModel {
    public void AddBank(IBankController bank);
    public IBankController GetBank(String name);
}
