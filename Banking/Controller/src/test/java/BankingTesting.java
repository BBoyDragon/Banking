import org.example.AccountsModel.Pair;
import org.example.Core.MainBankController;
import org.example.ModelAbstractions.*;
import org.example.ClientModel.ClientModel;
import org.example.Core.Accounts.DebetAccountController;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankingTesting {
    @Test
    public void TestAdd(){
        IClientModelBuilder builder = new ClientModel.ClientModelBuilder("Alexandr","Gushenko").WithPassport("1488").WithAddress("lensoveta 3");
        IAccountController accountController = new DebetAccountController(builder.Create(),2,3000,1.4f);
        accountController.AddMoney(500);
        assertEquals(500, accountController.GetBalance());

    }
    @Test
    public void TestRemove(){
        IClientModelBuilder builder = new ClientModel.ClientModelBuilder("Alexandr","Gushenko").WithPassport("1488").WithAddress("lensoveta 3");
        IAccountController accountController = new DebetAccountController(builder.Create(),2,3000,1.4f);
        accountController.AddMoney(500);
        accountController.RemoveMoney(300);
        assertEquals(200, accountController.GetBalance());

    }
    @Test
    public void TestTransit(){

        IMainBankController mainBankController = new MainBankController(new MainBankModel());
        mainBankController.RegisterNewBank("tinkoff",3000,5000,1.4f,new ArrayList<Pair>());
        IBankController tinkoff= mainBankController.GetBank("tinkoff");

        tinkoff.CrateClient(new ClientModel.ClientModelBuilder("Alexandr","Gushenko").WithPassport("1488").WithAddress("lensoveta 3"));
        tinkoff.CrateClient(new ClientModel.ClientModelBuilder("Kirill","Kirsanov").WithPassport("228").WithAddress("polevaya 15"));

        tinkoff.CreateDebetAccount("Alexandr","Gushenko");
        tinkoff.CreateDebetAccount("Kirill","Kirsanov");

        tinkoff.AddMoneyOnAccount(1,5000);
        tinkoff.TransferMoneyBetweenAccounts(1,2,3000);

        assertEquals(2000, tinkoff.GetAccount(1).GetBalance());
        assertEquals(3000, tinkoff.GetAccount(2).GetBalance());

    }



}
