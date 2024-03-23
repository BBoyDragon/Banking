package org.example.ModelAbstractions;

import java.util.ArrayList;
import java.util.List;

public class MainBankModel implements IMainBankModel{
    public List<IBankController> _bankControllers = new ArrayList<>();

    @Override
    public void AddBank(IBankController bank) {
        _bankControllers.add(bank);
    }

    @Override
    public IBankController GetBank(String name) {
        for(int i=0; i< _bankControllers.stream().count();i++){
            if (_bankControllers.get(i).get_name() == name){
                return _bankControllers.get(i);
            }
        }
        return null;
    }
}
