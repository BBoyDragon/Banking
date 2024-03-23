package org.example.View;

import lombok.AllArgsConstructor;
import org.example.AccountsModel.Pair;
import org.example.ClientModel.ClientModel;
import org.example.ModelAbstractions.IBankController;
import org.example.ModelAbstractions.IClientModelBuilder;
import org.example.ModelAbstractions.IMainBankController;
import picocli.CommandLine;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "MBCommand")
@AllArgsConstructor
public class MainBankCommand implements Callable<Integer> {
    private IMainBankController mainBankController;

    @CommandLine.Command(name = "RegisterBank")
    public void RegisterBankCommand(@CommandLine.Option(required = true, names = "name") String name,
                                    @CommandLine.Option(required = true, names = "limit") float limit,
                                    @CommandLine.Option(required = true, names = "debt") float debt,
                                    @CommandLine.Option(required = true, names = "fPercent")  float fixedPercent,
                                    @CommandLine.Option(required = true, names = "dPerKey") List<Float> fixedPercentkeys,
                                    @CommandLine.Option(required = true, names = "dPerVal") List<Float> fixedPercentValues){
        List<Pair> differentPercent = new ArrayList<>();
        for (int i=0;i<fixedPercentkeys.size();i++){
            differentPercent.add(new Pair(fixedPercentkeys.get(i),fixedPercentValues.get(i)));
        }
        mainBankController.RegisterNewBank(name,limit,debt,fixedPercent,differentPercent);
    }

    @CommandLine.Command(name = "TransferBetweenBanks")
    public void TransferBetweenBanks(@CommandLine.Option(required = true, names = "fromBank") String fromBank,
                                    @CommandLine.Option(required = true, names = "fromId") int fromId,
                                    @CommandLine.Option(required = true, names = "toBank") String toBank,
                                    @CommandLine.Option(required = true, names = "toId")  int toId,
                                    @CommandLine.Option(required = true, names = "amount") float amount){
        mainBankController.TransferMoneyBetweenBanks(fromBank,fromId,toBank,toId, amount);
    }
    @CommandLine.Command(name = "UndoTransaction")
    public void UndoTransaction(@CommandLine.Option(required = true, names = "d") int id,
                                    @CommandLine.Option(required = true, names = "frombank") String fromBank,
                                    @CommandLine.Option(required = true, names = "toBank") String toBank){
        mainBankController.UndoTransaction(id, fromBank, toBank);
    }
    @CommandLine.Command(name = "CreateDebetAccount")
    public void CreateDebetAccount(@CommandLine.Option(required = true, names = "bank") String bank,
                                @CommandLine.Option(required = true, names = "name") String name,
                                @CommandLine.Option(required = true, names = "secondName") String secondName){
        IBankController bankController = mainBankController.GetBank(bank);
        bankController.CreateDebetAccount(name,secondName);
    }
    @CommandLine.Command(name = "CreateDpositAccount")
    public void CreateDepositAccount(@CommandLine.Option(required = true, names = "bank") String bank,
                                   @CommandLine.Option(required = true, names = "name") String name,
                                   @CommandLine.Option(required = true, names = "secondName") String secondName,
                                   @CommandLine.Option(required = true, names = "debt") int debt){
        IBankController bankController = mainBankController.GetBank(bank);
        bankController.CreateDepositAccount(name,secondName,debt);
    }
    @CommandLine.Command(name = "CreateCreditAccount")
    public void CreateCreditAccount(@CommandLine.Option(required = true, names = "bank") String bank,
                                   @CommandLine.Option(required = true, names = "name") String name,
                                   @CommandLine.Option(required = true, names = "secondName") String secondName){
        IBankController bankController = mainBankController.GetBank(bank);
        bankController.CreateCreditAccount(name,secondName);
    }
    @CommandLine.Command(name = "CreateClient")
    public void CreateClient(@CommandLine.Option(required = true, names = "bank") String bank,
                                   @CommandLine.Option(required = true, names = "name") String name,
                                   @CommandLine.Option(required = true, names = "secondName") String secondName,
                                   @CommandLine.Option(names = "pasport") String pasport,
                                   @CommandLine.Option(names = "address") String address){
        IBankController bankController = mainBankController.GetBank(bank);
        IClientModelBuilder builder = new ClientModel.ClientModelBuilder(name,secondName);
        if(pasport!=null){
            builder.WithPassport(pasport);
        }
        if (address!=null){
            builder.WithAddress(address);
        }
        bankController.CrateClient(builder);
    }

    @CommandLine.Command(name = "AddMoneyOnAccount")
    public void AddMoneyOnAccount(@CommandLine.Option(required = true, names = "bank") String bank,
                                    @CommandLine.Option(required = true, names = "id") int id,
                                    @CommandLine.Option(required = true, names = "amount") float amount){
        IBankController bankController = mainBankController.GetBank(bank);
        bankController.AddMoneyOnAccount(id, amount);
    }
    @CommandLine.Command(name = "RemoveMoneyFromAccount")
    public void RemoveMoneyFromAccount(@CommandLine.Option(required = true, names = "bank") String bank,
                                  @CommandLine.Option(required = true, names = "id") int id,
                                  @CommandLine.Option(required = true, names = "amount") float amount){
        IBankController bankController = mainBankController.GetBank(bank);
        bankController.RemoveMoneyFromAccount(id, amount);
    }
    @CommandLine.Command(name = "TransitMoneybetweenAccount")
    public void TransitMoneybetweenAccount(@CommandLine.Option(required = true, names = "bank") String bank,
                                       @CommandLine.Option(required = true, names = "idFrom") int idFrom,
                                       @CommandLine.Option(required = true, names = "idTo") float idTo,
                                       @CommandLine.Option(required = true, names = "amount") float amount){
        IBankController bankController = mainBankController.GetBank(bank);
        bankController.TransferMoneyBetweenAccounts(idFrom,idFrom,amount);
    }
    @CommandLine.Command(name = "TimeSkip")
    public void TimeSkip(@CommandLine.Option(required = true, names = "bank") String bank,
                                           @CommandLine.Option(required = true, names = "days") int days){
        IBankController bankController = mainBankController.GetBank(bank);
        bankController.TimeSkip(days);
    }
    @CommandLine.Command(name = "UpdateLimit")
    public void UpdateLimit(@CommandLine.Option(required = true, names = "bank") String bank,
                         @CommandLine.Option(required = true, names = "limit") float limit){
        IBankController bankController = mainBankController.GetBank(bank);
        bankController.UpdateLimit(limit);
    }
    @CommandLine.Command(name = "UpdateFixedPercent")
    public void UpdateFixedPercent(@CommandLine.Option(required = true, names = "bank") String bank,
                            @CommandLine.Option(required = true, names = "percent") float percent){
        IBankController bankController = mainBankController.GetBank(bank);
        bankController.UpdateFixedPercent(percent);
    }
    @CommandLine.Command(name = "UpdatedifferentPercent")
    public void UpdatedifferentPercent(@CommandLine.Option(required = true, names = "bank") String bank,
                                       @CommandLine.Option(required = true, names = "dPerKey") List<Float> fixedPercentkeys,
                                       @CommandLine.Option(required = true, names = "dPerVal") List<Float> fixedPercentValues){
        List<Pair> differentPercent = new ArrayList<>();
        for (int i=0;i<fixedPercentkeys.size();i++){
            differentPercent.add(new Pair(fixedPercentkeys.get(i),fixedPercentValues.get(i)));
        }
        IBankController bankController = mainBankController.GetBank(bank);
        bankController.UpdateDifferentPercent(differentPercent);
    }
    @CommandLine.Command(name = "GetBalance")
    public void GetBalance(@CommandLine.Option(required = true, names = "bank") String bank,
                                   @CommandLine.Option(required = true, names = "id") int id){
        IBankController bankController = mainBankController.GetBank(bank);
        System.out.print(bankController.GetAccount(id).GetBalance());
    }



    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
