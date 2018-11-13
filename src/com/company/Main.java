package com.company;

import java.io.IOException;
import java.lang.String;

public class Main {



    public static void main(String[] args) {
        Command cmd = null;
        //C:\Program Files\Java\jdk1.8.0_181\bin
        //C:\Users\Юля\Desktop\Java\UtilityForFiles

        if (args.length == 0) {
            Command.errors.add(new MyError(Error.valueOf("COMMAND_NOT_EXIST")));
        } else {
            String s_cmd = args[0].toLowerCase();
            String[] options = new String[args.length - 1];
            for (int i = 0; i < options.length; i++) {
                options[i] = args[i+1];
            }

            if (s_cmd.equals("ls")) {
                cmd = new Ls(options);
            } else if (s_cmd.equals("mkdir")) {
                cmd = new Mkdir(options);
            } else if (s_cmd.equals("echo")) {
                cmd = new Echo(options);
            } else if (s_cmd.equals("cat")) {
                cmd = new Cat(options);
            } else {
                Command.errors.add(new MyError(Error.valueOf("COMMAND_NOT_EXIST")));
            }
            //cmd = new Ls("C:\Users\Юля\Desktop\Java1","-R");
            //cmd = new Mkdir("C:\\Users\\Юля\\Desktop\\Java1","Java8");
            //cmd = new Echo("C:\\Users\\Юля\\Desktop\\Java1","Java8 - the best", "Java8.txt");
            //cmd = new Cat("C:\\Users\\Юля\\Desktop\\Java1","-En", "Java8.txt", "Java3.txt", "Hello world.txt");

            if (cmd == null || cmd.getStateCode() != 0) {
                Command.errors.add(new MyError(Error.valueOf("INVALID_NUMBER_OF_ARGUMENTS")));
            } else {
                try {
                    cmd.execute();
                } catch (IOException ex) {
                    Command.errors.add(new MyError(Error.valueOf("EXECUTION_ERROR")));
                }
            }
        }
        for (MyError item : Command.errors){
            item.ErrorMessage();
        }
    }
}
