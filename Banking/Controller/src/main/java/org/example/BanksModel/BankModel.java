package org.example.BanksModel;

import lombok.Getter;
import org.example.ModelAbstractions.IAccountController;
import org.example.ModelAbstractions.IBankModel;
import org.example.ModelAbstractions.IClientModel;
import org.example.ModelAbstractions.ITransaction;
import org.example.AccountsModel.Pair;
import org.example.Core.Accounts.IDifferentPercent;
import org.example.Core.Accounts.IFixedPercent;

import java.util.ArrayList;
import java.util.List;
public class BankModel implements IBankModel {

    @Getter
    private String _name;
    private List<IClientModel> _clientModels= new ArrayList<>();
    private List<IAccountController>_accountControllers= new ArrayList<>();
    private int _curentId=0;
    @Getter
    private float _limit;
    @Getter
    private float _debt;
    @Getter
    private float _fixedPercent;
    @Getter
    private List<Pair> _differentPercent;
    private List<ITransaction> _transactions;
    public void AddClient(IClientModel model){
        _clientModels.add(model);
    }

    @Override
    public IClientModel GetClient(String name, String secondName) {
        for (int i=0;i<_clientModels.stream().count();i++){
            if (_clientModels.get(i).get_name()==name&&_clientModels.get(i).get_secondName()==secondName){
                return _clientModels.get(i);
            }
        }
        return null;
    }

    @Override
    public IAccountController GetAccount(int Id) {
        for (int i=0;i<_accountControllers.stream().count();i++){
            if (_accountControllers.get(i).GetId()==Id){
                return _accountControllers.get(i);
            }
        }
        return null;
    }

    @Override
    public void AddAccount(IAccountController accountController) {
        _accountControllers.add(accountController);
    }

    @Override
    public int GenerateAccountId() {
        _curentId++;
        return _curentId;
    }

    @Override
    public void TimeSkip(int days) {
        for (int i=0;i<_accountControllers.stream().count();i++){
            _accountControllers.get(i).TimeSkip(days);
        }
    }

    @Override
    public void UpdateLimit(float limit) {
        for (int i=0;i<_accountControllers.stream().count();i++){
            _accountControllers.get(i).UpdateUntrustableLimit(limit);
        }
    }

    @Override
    public void UpdateFixedPercent(float percent) {
        for (int i=0;i<_accountControllers.stream().count();i++){
            if(_accountControllers.get(i) instanceof IFixedPercent fixedPercent){
                fixedPercent.UpdateFixedPercent(percent);
            }
        }
    }

    @Override
    public void UpdateDifferentPercent(List<Pair> percent) {
        for (int i=0;i<_accountControllers.stream().count();i++){
            if(_accountControllers.get(i) instanceof IDifferentPercent differentPercent){
                differentPercent.UpdateDifferentPercent(percent);
            }
        }
    }

    @Override
    public ITransaction GetAndRemoveTransaction(int id) {
        ITransaction transaction = null;
        for (int i=0;i<_transactions.size();i++){
            if (_transactions.get(i).get_id()==id){
                transaction= _transactions.get(i);
                break;
            }
        }
        if (transaction!= null){
            _transactions.remove(transaction);
            return transaction;
        }else {
            return null;
        }
    }

    @Override
    public void AddTransaction(ITransaction transaction) {
        _transactions.add(transaction);
    }

    public BankModel(String name, float limit, float debt, float fixedPercent, List<Pair> differentPercent) {
        _name=name;
        _limit=limit;
        _debt = debt;
        _fixedPercent=fixedPercent;
        _differentPercent = differentPercent;
        _transactions = new ArrayList<>();
    }
}
