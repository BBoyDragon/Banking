package org.example.Core.Accounts;

import org.example.ModelAbstractions.IAccountController;
import org.example.ModelAbstractions.IAccountModel;
import org.example.ModelAbstractions.IClientModel;
import org.example.AccountsModel.AccountModel;
import org.example.AccountsModel.AccountOperationResult;
import org.example.ClientModel.ClientStatus;

public class DebetAccountController implements IAccountController, IFixedPercent {

    public DebetAccountController(IClientModel clientModel, int id, float limit, float percent){
        _clientModel = clientModel;
        _accountModel= new AccountModel(id);
        _limit=limit;
        _percent = percent;
    }
    private IClientModel _clientModel;
    private IAccountModel _accountModel;
    private float _limit;
    private float _percent;
    private float _curentBonus;
    private int _curentAmountOfDays;
    @Override
    public AccountOperationResult RemoveMoney(float amount) {
        if(_clientModel.GetClientStatus()== ClientStatus.untrustable && amount>_limit){
            return AccountOperationResult.failure;
        }

        if(_accountModel.get_amountOfMoney()>0){
            _accountModel.RemoveMoney(amount);
            return AccountOperationResult.success;
        }

        return AccountOperationResult.failure;
    }

    @Override
    public AccountOperationResult AddMoney(float amount) {
        _accountModel.AddMoney(amount);
        return AccountOperationResult.success;
    }

    @Override
    public void UpdateUntrustableLimit(float limit) {
        _limit = limit;
    }
    @Override
    public int GetId() {
        return _accountModel.get_id();
    }

    @Override
    public void TimeSkip(int days) {
       for (int i=0;i<days;i++){
           _curentAmountOfDays++;
           _curentBonus+=_percent/365*_accountModel.get_amountOfMoney();
           if (_curentAmountOfDays==30){
               _accountModel.AddMoney(_curentBonus);
               _curentBonus=0;
               _curentAmountOfDays=0;
           }
       }

    }

    @Override
    public float GetBalance() {
        return _accountModel.get_amountOfMoney();
    }
    @Override
    public void UpdateFixedPercent(Float percent) {
        _percent=percent;
    }
}
