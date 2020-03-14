package com.shiv;


import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Driver  {

    public static void main(String[] args) {
        //Calculator c = new Calculator();
        //c.start();

        //now MVC pattern
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
