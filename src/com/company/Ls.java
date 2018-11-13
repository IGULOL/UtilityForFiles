package com.company;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Выводит названия директорий и файлов в текущей директории,
 * отсортированных по имени, по возрастанию.
 * Ключи:
 *   -r - опциональный ключ, который указывает, что нужна
 * сортировка в обратном порядке.
 */
public class Ls extends Command {
    //опция сортировки в обратном порядке
    private Option OPTION_REVERSE_SORT = new Option("-r", false);
    //опция рекурсивного вывода файлов и директорий, находящихся в указанной директории
    private Option OPTION_DIRECTORY_CONTENTS = new Option("-R", false);

    @Override
    public String getName() {
        return "ls";
    }
    @Override
    public List<String> getKeys() {
        return Arrays.asList("-r", "-R");
    }

    /**
     * Конструктор команды. При хотя бы одном неверном параметре меняет
     * состояние кода ошибки на отрицательное значение.
     * @param args 0 - опциональный ключ или его отсутствие
     */
    public Ls(String[] args){

        directory = System.getProperty("user.dir");

        int i = 0;
        int j = 0;
        while (i < args.length) {
            j = getKeys().indexOf((args[i]));
            switch (j) {
                case 0:
                    OPTION_REVERSE_SORT.setState(true);
                    break;
                case 1:
                    OPTION_DIRECTORY_CONTENTS.setState(true);
                    break;
                default:
                    errors.add(new MyError(Error.valueOf("OPTION_NOT_EXIST")));
                    stateCode = -1;
                    break;
            }
            i++;
        }
    }

    /**
     * Сортировка файлов
     * @param files
     */
    private void sortFiles(List<File> files){
        if (OPTION_REVERSE_SORT.getState() == false){
            Collections.sort(files, (f1,f2) -> f1.getName().compareTo(f2.getName()));
        } else {
            Collections.sort(files, (f1,f2) -> f2.getName().compareTo(f1.getName()));
        }
    }

    /**
     * Печать файлов
     * @param files
     */
    private void printFiles(List<File> files){
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    /**
     * Рекурсивная печать всех директорий и файлов в них, начиная с dir
     * @param dir
     */
    private void printAllFiles(File dir){
        File[] files = dir.listFiles();

        List<File> filesList = Arrays.asList(files);
        sortFiles(filesList);

        //сортировка по папкам и после этого печать файлов
        //printFiles(filesList);

        for (File file : files){
            //печать отсортированных файлов по пути обхода
            System.out.println(file.getName());
            if (file.isDirectory()){
                File _dir = new File(file.getAbsolutePath());
                printAllFiles(_dir);
            }
        }
    }

    /**
     * Выполнение команды
     * @return stateCode
     */
    public int execute(){
        if (stateCode >= 0){
            List<File> filesList = new LinkedList<File>();
            File file = new File(directory);

            //если не включена опция "-R"
            if (OPTION_DIRECTORY_CONTENTS.getState() == false){
                File[] files = file.listFiles();
                filesList = Arrays.asList(files);
                sortFiles(filesList);
                printFiles(filesList);
            } else {
                printAllFiles(file);
            }
        }
        return stateCode;
    }
}
