package behavioral.command.demo.command;

/**
 * @author : zhenyun.su
 * @comment : 空命令对象
 * @since : 2019/8/20
 */

public class NullCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Null Command execute");
    }

    @Override
    public void undo() {
        System.out.println("Null Command undo");
    }
}
