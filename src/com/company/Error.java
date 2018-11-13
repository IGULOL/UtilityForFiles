package com.company;

/**
 * Виды ошибок
 */
public enum Error implements IError {
    DIRECTORY_NOT_EXIST(){
        @Override
        public String getMessage(){
            return new String("Директория не существует.");
        }
        @Override
        public int getCodeError(){
            return -1;
        }
    },
    FILE_NOT_EXIST(){
        @Override
        public String getMessage(){
            return new String("Файл не существует.");
        }
        @Override
        public int getCodeError(){
            return -2;
        }
    },
    FILE_EXIST(){
        @Override
        public String getMessage(){
            return new String("Файл уже существует.");
        }
        @Override
        public int getCodeError(){
            return -3;
        }
    },
    FILE_WRITE(){
        @Override
        public String getMessage(){
            return new String("Ошибка записи в файл.");
        }
        @Override
        public int getCodeError(){
            return -4;
        }
    },
    FILE_READ(){
        @Override
        public String getMessage(){
            return new String("Ошибка чтения из файла.");
        }
        @Override
        public int getCodeError(){
            return -5;
        }
    },
    COMMAND_NOT_EXIST(){
        @Override
        public String getMessage(){
            return new String("Команда не существует.");
        }
        @Override
        public int getCodeError(){
            return -6;
        }
    },
    OPTION_NOT_EXIST{
        @Override
        public String getMessage(){
            return new String("Опция не существует.");
        }
        @Override
        public int getCodeError(){
            return -7;
        }
    },
    INCORRECT_NAME_FILE{
        @Override
        public String getMessage(){
            return new String("Неккоректное имя файла.");
        }
        @Override
        public int getCodeError(){
            return -8;
        }
    },
    INVALID_NUMBER_OF_ARGUMENTS{
        @Override
        public String getMessage(){
            return new String("Неверное количество аргументов.");
        }
        @Override
        public int getCodeError(){
            return -9;
        }
    },
    EXECUTION_ERROR{
        @Override
        public String getMessage(){
            return new String("Ошибка выполнения команды.");
        }
        @Override
        public int getCodeError(){
            return -10;
        }
    };
}
