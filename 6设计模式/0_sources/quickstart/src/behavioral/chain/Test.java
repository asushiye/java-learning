package behavioral.chain;

import behavioral.chain.start.AbstractLogger;
import behavioral.chain.start.ConsoleLogger;
import behavioral.chain.start.FileLogger;
import behavioral.chain.start.DataBaseLogger;

/**
 * @author : zhenyun.su
 * @comment : 适配器模式 测试
 * @since : 2019/8/8
 */

public class Test {

    public static AbstractLogger getLoggerChain(){
        ConsoleLogger consoleLogger =  new ConsoleLogger(AbstractLogger.INFO);
        FileLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        DataBaseLogger dataBaseLogger = new DataBaseLogger(AbstractLogger.ERROR);

        dataBaseLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);
        return dataBaseLogger;
    }

    public static void main(String[] args) {
        // 类适配器
        startTest();

    }

    public static void startTest(){
        AbstractLogger abstractLogger = Test.getLoggerChain();
        abstractLogger.logMessage(AbstractLogger.INFO,"This is a info level information.");
        abstractLogger.logMessage(AbstractLogger.DEBUG,"This is a debug level information.");
        abstractLogger.logMessage(AbstractLogger.ERROR,"This is an error level information.");
    }
}
