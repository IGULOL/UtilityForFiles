package com.company;

import java.io.*;
import java.util.List;

/**
 * Создает в текущей директории текстовый файл с указанным именем и
 * содержимым. В случае, если файл уже существует, печатает ошибку в
 * stderr и возвращается с ненулевым статус кодом.
 * Параметры:
 *   Параметр 1 - содержимое файла (текст)
 *   Параметр 2 - имя файла
 */
public class Echo extends Command{

    private String newName;
    private String text;

    @Override
    public String getName() {
        return "echo";
    }
    @Override
    public List<String> getKeys() {
        return null;
    }

    /**
     * Конструктор команды
     * @param args 0 - текст, который необходимо записать в файл newName
     * 1 - имя нового файла
     */
    public Echo(String[] args){

        directory = System.getProperty("user.dir");

        if (new File(args[1]).exists()) {
            errors.add(new MyError(Error.valueOf("FILE_EXIST")));
            stateCode = -1;
        }

        this.text = args[0];
        this.newName = args[1];
    }

    /**
     * Выполнение команды
     * @return stateCode
     * @throws IOException
     */
    public int execute() throws IOException {
        if (stateCode < 0){
            return stateCode;
        } else {
            File file = new File(directory + "\\" + newName);
            try(FileWriter writer = new FileWriter(file.getAbsolutePath(), false))
            {
                writer.write(text);
                writer.flush();
            }
            catch(IOException ex){
                errors.add(new MyError(Error.valueOf("FILE_WRITE")));
                stateCode = -1;
            }
        }
        return stateCode;
    }
}

