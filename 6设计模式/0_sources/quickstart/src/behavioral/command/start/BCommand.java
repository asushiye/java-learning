package behavioral.command.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/15
 */

public class BCommand implements Command {
    private BReceived bReceived;

    public BCommand() {
        this.bReceived = new BReceived();
    }
    @Override
    public void execute() {
        this.bReceived.doing();
    }
}
