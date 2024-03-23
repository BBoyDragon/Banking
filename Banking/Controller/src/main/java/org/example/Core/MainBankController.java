package org.example.Core;
import org.example.ModelAbstractions.IBankController;
import org.example.ModelAbstractions.IMainBankController;
import org.example.ModelAbstractions.IMainBankModel;
import org.example.ModelAbstractions.ITransaction;
import org.example.AccountsModel.Pair;
import org.example.BanksModel.BankModel;
import org.example.BanksModel.Transaction;
import org.example.BanksModel.TransactionResult;

import java.util.List;

public class MainBankController implements IMainBankController{

    public MainBankController(IMainBankModel model){
        _model = model  ;
    }
    private IMainBankModel _model;
    private int _transactionIdNumber;
    @Override
    public void RegisterNewBank(String name, float limit, float debt,float fixedPercent, List<Pair> differentPercent) {
        IBankController bank = new BankController(new BankModel(name, limit, debt,fixedPercent,differentPercent));
        _model.AddBank(bank);
    }

    @Override
    public MainBankResult TransferMoneyBetweenBanks(String fromBank,int fromId, String toBank,int toId,float amount) {
        _transactionIdNumber++;
        ITransaction transaction= new Transaction(_model.GetBank(fromBank),fromId,_model.GetBank(toBank),toId,amount,_transactionIdNumber);
        if(transaction.Do() == TransactionResult.Failure){
            return MainBankResult.Error;
        }
        _model.GetBank(fromBank).RegisterTransaction(transaction.GetUndo());
        _model.GetBank(toBank).RegisterTransaction(transaction.GetUndo());
        return MainBankResult.Success;
    }

    @Override
    public MainBankResult UndoTransaction(int id, String fromBank, String toBank) {
        _model.GetBank(fromBank).UndoTransaction(id);
        _model.GetBank(toBank).UndoTransaction(id);
        return MainBankResult.Success;
    }

    @Override
    public IBankController GetBank(String name) {
       return _model.GetBank(name);
    }
}
