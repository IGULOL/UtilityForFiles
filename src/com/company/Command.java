package com.company;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public abstract class Command {

    public static List<MyError> errors = new LinkedList<MyError>();

    protected int stateCode = 0;
    public int getStateCode(){
        return stateCode;
    }

    //имя команды
    public abstract String getName();
    //ключи комманды
    public abstract List<String> getKeys();

    protected String directory = "";

    public abstract int execute() throws IOException;
}
