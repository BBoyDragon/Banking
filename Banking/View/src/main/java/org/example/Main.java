package org.example;

import org.example.Core.MainBankController;
import org.example.ModelAbstractions.IMainBankController;
import org.example.ModelAbstractions.MainBankModel;
import org.example.View.MainBankCommand;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IMainBankController bankController = new MainBankController(new MainBankModel());
        Scanner in = new Scanner(System.in);
        String[] ar = in.nextLine().split(" ");
        int exitCode=0;
        while(ar[0]!=""){
            exitCode = new CommandLine(new MainBankCommand(bankController)).execute(ar);
            ar = in.nextLine().split(" ");
        }
        System.exit(exitCode);
    }
}