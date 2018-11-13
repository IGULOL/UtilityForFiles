package com.company;

public interface IOption {
    String getName();

    boolean getState();

    /**
     * Устанавливает состояние опции
     * @param state новое состояние
     */
    void setState(boolean state);
}
