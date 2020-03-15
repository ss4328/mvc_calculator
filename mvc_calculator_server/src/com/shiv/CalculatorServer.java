package com.shiv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {
    public CalculatorServer() throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("The server is running...");

        while(true){
            Socket client = server.accept();
            RequestHandler rh = new RequestHandler(client);
            rh.start();
        }
    }
}
