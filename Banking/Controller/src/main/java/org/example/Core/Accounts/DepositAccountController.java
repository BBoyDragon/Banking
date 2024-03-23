package org.example.Core.Accounts;

import org.example.ModelAbstractions.IAccountController;
import org.example.ModelAbstractions.IAccountModel;
import org.example.ModelAbstractions.IClientModel;
import org.example.AccountsModel.AccountModel;
import org.example.AccountsModel.AccountOperationResult;
import org.example.AccountsModel.Pair;
import org.example.ClientModel.ClientStatus;

import java.util.List;

public class DepositAccountController implements IAccountController, IDifferentPercent {
    public DepositAccountController(IClientModel clientModel, int id, int amountOfTerm, float limit, List<Pair> differentPercent){
        _clientModel = clientModel;
        _amountOfTerm = amountOfTerm;
        _accountModel= new AccountModel(id);
        _limit=limit;
        _differentPercent=differentPercent;
    }
    private IClientModel _clientModel;
    private IAccountModel _accountModel;
    private int _amountOfTerm;
    private float _limit;
    private List<Pair> _differentPercent;
    private float _curentBonus;
    private int _curentAmountOfDays;
    @Override
    public AccountOperationResult RemoveMoney(float amount) {
        if (_amountOfTerm>0){
            return AccountOperationResult.failure;
        }

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
    public float GetBalance() {
        return _accountModel.get_amountOfMoney();
    }

    @Override
    public void TimeSkip(int days) {
        for (int i=0;i<days;i++){
            _curentAmountOfDays++;
            Pair percentKey= new Pair(0,0);
            for(int j = 0;j<_differentPercent.stream().count();j++ ){
                if (_accountModel.get_amountOfMoney()+_curentBonus<=_differentPercent.get(j).get_sum() && _differentPercent.get(j).get_sum()>percentKey.get_sum()){
                    percentKey=_differentPercent.get(i);
                }
            }
            _curentBonus+= percentKey.get_percent()/365*_accountModel.get_amountOfMoney();
            if (_curentAmountOfDays==30){
                _accountModel.AddMoney(_curentBonus);
                _curentBonus=0;
                _curentAmountOfDays=0;
            }
        }
    }

    @Override
    public void UpdateDifferentPercent(List<Pair> percent) {
        _differentPercent=percent;
    }
}
