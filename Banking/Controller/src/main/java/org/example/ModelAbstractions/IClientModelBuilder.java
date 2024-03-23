package org.example.ModelAbstractions;

public interface IClientModelBuilder {
    public IClientModel Create();
    public IClientModelBuilder WithPassport(String passport);
    public IClientModelBuilder WithAddress(String Address);
}
