package org.example.AccountsModel;

import lombok.Getter;
import org.example.ModelAbstractions.IAccountModel;

public class AccountModel implements IAccountModel {
    public AccountModel(int id){
        _id=id;
    }
    @Getter
    private int _id;
    @Getter
    private float _amountOfMoney;

    @Override
    public void AddMoney(float amount) {
        _amountOfMoney+=amount;
    }

    @Override
    public void RemoveMoney(float amount) {
        _amountOfMoney-=amount;
    }
}
