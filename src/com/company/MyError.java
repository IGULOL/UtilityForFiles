package com.company;

/**
 * Ошибка
 */
public class MyError {
    private Error err;

    public MyError(Error err){
        this.err = err;
    }

    public void ErrorMessage(){
        System.err.println("Ошибка " + err.name() + ".");
        System.err.println(err.getMessage());
        System.err.println("Код ошибки: " + err.getCodeError());
    }
}
