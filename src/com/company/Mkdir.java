package com.company;

import java.io.*;
import java.util.List;

/**
 * Создает в текущей директории новую директорию с указанным
 * именем. В случае, если директория уже существует, печатает ошибку
 * в stderr и возвращается с ненулевым статус кодом.
 * Параметры:
 *   Параметр 1 - имя новой директории
 */
public class Mkdir extends Command{

    private String newName;

    @Override
    public String getName() {
        return "mkdir";
    }
    @Override
    public List<String> getKeys() {
        return null;
    }
    /**
     * Конструктор команды
     * @param args 0 - имя новой директории
     */
    public Mkdir(String[] args){

        directory = System.getProperty("user.dir");

        if (new File(args[0]).exists()) {
            errors.add(new MyError(Error.valueOf("FILE_EXIST")));
            stateCode = -1;
        }

        this.newName = args[0];
    }

    /**
     * Выполнение команды
     * @return stateCode
     */
    public int execute() throws IOException{
        if (stateCode < 0){
            return stateCode;
        } else {
            File file = new File(directory + "\\" + newName);
            file.mkdir();
        }
        return stateCode;
    }
}


