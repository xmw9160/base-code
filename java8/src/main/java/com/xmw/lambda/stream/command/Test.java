package com.xmw.lambda.stream.command;

public class Test {
    public static void main(String[] args) {
//        Macro macro = new Macro();
        Editor editor = new Editor() {
            @Override
            public void save() {

            }

            @Override
            public void open() {

            }

            @Override
            public void close() {

            }
        };
//        macro.record(new Open(editor));
//        macro.record(new Save(editor));
//        macro.record(new Close(editor));
//        macro.run();

        Macro macro = new Macro();
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
        macro.run();
    }
}
