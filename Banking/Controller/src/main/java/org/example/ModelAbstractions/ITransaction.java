package org.example.ModelAbstractions;

import org.example.BanksModel.TransactionResult;

public interface ITransaction {
    public TransactionResult Do();
    public ITransaction GetUndo();
    public int get_id();
}
