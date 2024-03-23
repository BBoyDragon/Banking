package org.example.ModelAbstractions;

import org.example.ClientModel.ClientStatus;

public interface IClientModel {
    public String get_name();
    public String get_secondName();
    public void SetPassport(String passport);
    public void SetAddress(String address);
    public ClientStatus GetClientStatus();
}
