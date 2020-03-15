package com.shiv.Client;

import com.shiv.VisitorLib.Equation;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public void sendToServer(Equation eq) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(eq);
            output.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error in sending Equation object to the server");
        }

    }
}
