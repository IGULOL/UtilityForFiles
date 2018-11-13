package com.company;

public class Option implements IOption{
    private boolean state;
    private String name;

    public Option(String name, boolean state){
        this.state = state;
        this.name = name;
    }

    @Override
    public String getName(){
        return name;
    };

    @Override
    public boolean getState(){
        return state;
    };

    @Override
    public void setState(boolean state){
        this.state = state;
    }
}
