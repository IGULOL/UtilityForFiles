package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Выводит содержимое указанного текстового файла (или нескольких
 * файлов). В случае вывода нескольких файлов их содержимое
 * показывается последовательно. Если один или несколько файлов не
 * существуют, выводит в stderr сообщение об этом файле.
 * Параметры:
 *   Параметр 1...N - имя файла
 */
public class Cat extends Command{

    //показывать символ $ в конце каждой строки
    private Option OPTION_END_STR = new Option("-E", false);
    //нумеровать все строки
    private Option OPTION_NUMBER_LINES = new Option("-n", false);
    //показывать символ $ в конце каждой строки и нумеровать все строки
    private Option OPTION_END_STR_NUMBER_LINES = new Option("-En", false);

    @Override
    public String getName() {
        return "cat";
    }
    @Override
    public List<String> getKeys() {
        return Arrays.asList("-E", "-n", "-En");
    }

    private String[] files;

    /**
     * Конструктор команды
     * @param args 0 - имя опций и файлов, которые необходимо прочитать
     */
    public Cat(String[] args){

        directory = System.getProperty("user.dir");

        int i = 0;
        int j = 0;
        boolean ok = false;
        while (i < args.length && !ok){
            j = getKeys().indexOf((args[i]));
            switch (j){
                case 0:
                    OPTION_END_STR.setState(true);
                    i++;
                break;
                case 1:
                    OPTION_NUMBER_LINES.setState(true);
                    i++;
                break;
                case 2:
                    OPTION_END_STR_NUMBER_LINES.setState(true);
                    i++;
                break;
                default: ok = true;
            }
        }
        if (i == args.length){
            errors.add(new MyError(Error.valueOf("FILE_NOT_EXIST")));
            stateCode = -1;
        } else {
            j = 0;
            files = new String[args.length - i];
            while (i < args.length){
                files[j] = args[i];
                i++;
                j++;
            }
        }
    }

    /**
     * Чтение из файла
     * @param file файл, который в данный момент читается
     * @throws IOException
     */
    Integer i = 1;

    private void inputDataFromFile(File file) throws IOException {
        try{
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream, "windows-1251"));
            String strLine;
            if (!br.ready()) {
                System.out.println("Файл " + file.getName() + " пуст.");
            }
            while ((strLine = br.readLine()) != null){
                if (OPTION_END_STR_NUMBER_LINES.getState()){
                    strLine += "$";
                    strLine = i + " " + strLine;
                    i++;
                } else {
                    if (OPTION_END_STR.getState()) {
                        strLine += "$";
                    }
                    if (OPTION_NUMBER_LINES.getState()){
                        strLine = i + " " + strLine;
                        i++;
                    }
                }
                System.out.println(strLine);
            }
            br.close();
        } catch (IOException ex){
            System.out.println("Не удалось прочитать данные из файла " + file.getName());
        }
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
            File file;
            i = 1;
            for (String nameFile : files){
                file = new File(directory + "\\" + nameFile);
                try {
                    inputDataFromFile(file);
                } catch (IOException ex) {}
            }
        }
        return stateCode;
    }
}
