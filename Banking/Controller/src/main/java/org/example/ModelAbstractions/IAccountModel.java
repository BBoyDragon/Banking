package org.example.ModelAbstractions;

public interface IAccountModel {
    public int get_id();
    public float get_amountOfMoney();
    public void AddMoney(float amount);
    public void RemoveMoney(float amount);
}
