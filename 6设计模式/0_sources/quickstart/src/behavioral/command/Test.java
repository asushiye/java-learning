package behavioral.command;

import behavioral.command.start.ACommand;
import behavioral.command.start.BCommand;
import behavioral.command.start.Command;
import behavioral.command.start.Invoker;

/**
 * @author : zhenyun.su
 * @comment : 命令模式 测试
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        // 类适配器
        startTest();

    }

    public static void startTest(){
        Command aCommand = new ACommand();
        Command bCommand = new BCommand();
        Invoker invoker = new Invoker();
        invoker.addCommand(aCommand);
        invoker.addCommand(bCommand);
        invoker.executeCommand();
    }
}
