package org.example.ModelAbstractions;

import org.example.AccountsModel.AccountOperationResult;

public interface IAccountController {
    public AccountOperationResult RemoveMoney(float amount);
    public AccountOperationResult AddMoney(float amount);
    public void UpdateUntrustableLimit(float limit);
    public int GetId();

    public float GetBalance();
    public void TimeSkip(int days);
}
