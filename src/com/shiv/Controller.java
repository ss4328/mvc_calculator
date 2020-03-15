package com.shiv;



public class Controller extends State {

    Model model;
    View view;

    public Controller(Model model, View view) {
        super();
        this.model = model;
        this.view = view;
    }

    public void handleUserInput(String s1, String op, String s2) {
        int i1 = Integer.parseInt(s1);
        int i2 = Integer.parseInt(s2);
        int res = this.model.calculate(i1, op, i2);
        this.view.setResult(String.valueOf(res));
        this.view.res = String.valueOf(res);
    }

    public void sendMessageToServer(){
        model.sendMessageToServer();
    }

    public void start() {
        this.view.show();
    }


}
