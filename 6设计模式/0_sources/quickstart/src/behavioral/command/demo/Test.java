package behavioral.command.demo;

import behavioral.command.demo.command.*;
import behavioral.command.demo.receiver.Fan;
import behavioral.command.demo.receiver.Light;
import behavioral.command.demo.receiver.Tv;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/20
 */

public class Test {
    public static void main(String[] args) {
//        controlTest();
        queueTest();
    }
    public static void controlTest(){
        Light light = new Light();
        LightOnCommand lightOnCommand =  new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        Tv  tv = new Tv();
        TvOnCommand tvOnCommand = new TvOnCommand(tv);
        TvOffCommand tvOffCommand = new TvOffCommand(tv);

        Fan fan =  new Fan();
        FanHighCommand fanHighCommand = new FanHighCommand(fan);
        FanMediumCommand fanMediumCommand = new FanMediumCommand(fan);
        FanLowCommand fanLowCommand = new FanLowCommand(fan);
        FanOffCommand fanOffCommand = new FanOffCommand(fan);

        InvokeControl invokeControl = new InvokeControl(7);
        invokeControl.setCommand(0, lightOnCommand, lightOffCommand);
        invokeControl.setCommand(1, tvOnCommand, tvOffCommand);
        invokeControl.setCommand(2, fanLowCommand, fanOffCommand);
        invokeControl.setCommand(3,fanMediumCommand, fanOffCommand);
        invokeControl.setCommand(4,fanHighCommand, fanOffCommand);

        invokeControl.onClick(0);
        invokeControl.offClick(0);
        invokeControl.undo();

        invokeControl.onClick(1);
        invokeControl.offClick(1);
        invokeControl.undo();
        invokeControl.onClick(2);
        invokeControl.offClick(2);
        invokeControl.undo();
        invokeControl.onClick(3);
        invokeControl.offClick(3);
        invokeControl.undo();
        invokeControl.onClick(4);
        invokeControl.offClick(4);

        invokeControl.onClick(5);
        invokeControl.offClick(5);

        PartyCommand partyOnComand = new PartyCommand();
        partyOnComand.addCommand(lightOnCommand);
        partyOnComand.addCommand(tvOnCommand);
        partyOnComand.addCommand(fanMediumCommand);

        PartyCommand partyOffComand = new PartyCommand();
        partyOffComand.addCommand(lightOffCommand);
        partyOffComand.addCommand(tvOffCommand);
        partyOffComand.addCommand(fanOffCommand);

        invokeControl.setCommand(6, partyOnComand, partyOffComand);
        invokeControl.onClick(6);
        invokeControl.offClick(6);
        invokeControl.undo();
    }

    public static void queueTest(){
        Light light = new Light();
        LightOnCommand lightOnCommand =  new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        Tv  tv = new Tv();
        TvOnCommand tvOnCommand = new TvOnCommand(tv);
        TvOffCommand tvOffCommand = new TvOffCommand(tv);

        Fan fan =  new Fan();
        FanHighCommand fanHighCommand = new FanHighCommand(fan);
        FanMediumCommand fanMediumCommand = new FanMediumCommand(fan);
        FanLowCommand fanLowCommand = new FanLowCommand(fan);
        FanOffCommand fanOffCommand = new FanOffCommand(fan);

        CommandQueue commandQueue = new CommandQueue();
        commandQueue.add(lightOnCommand);
        commandQueue.add(tvOnCommand);
        commandQueue.add(fanHighCommand);

        Command command = commandQueue.get();
        command.execute();

        command = commandQueue.get();
        command.execute();

        command = commandQueue.get();
        command.execute();

    }
}
