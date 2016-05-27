package com.zwli.pattern.command;

public class Invoker {
    private Command cmd;

    public void setCmd(final Command cmd) {
        this.cmd = cmd;
    }

    public void doAction() {
        this.cmd.execute();
    }
}
