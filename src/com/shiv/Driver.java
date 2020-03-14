package com.shiv;

public class Driver  {

    public static void main(String[] args) {
        //old way: Just instantiate calculator
        //Calculator c = new Calculator();
        //c.start();

        //Now a better way, MVC pattern
        //create model
        Model model = new Model();
        //add view as observer to model
        View view = new View();
        //create controller with model and view as args
        Controller controller = new Controller(model, view);
        view.setController(controller);
        controller.start();

    }
}
