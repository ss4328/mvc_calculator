package com.shiv;

import com.shiv.VisitorLib.Equation;
import com.shiv.VisitorLib.NodeDisplayVisitor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.io.FileWriter;

public class RequestHandler extends Thread {
    private Integer requestsNumber;
    private Socket clientSocket = null;
    private ArrayList<Equation> equationList;

    public RequestHandler(Socket _clientSocket){
        clientSocket = _clientSocket;
        requestsNumber = 0;
        equationList = new ArrayList<Equation>();
    }

    public void run(){
        Equation eqReceived =null;
        try {
            ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
            eqReceived = (Equation) input.readObject();
            clientSocket.close();
        } catch (Exception e) {
            System.err.println("Failed to read expression");
            e.printStackTrace();
        }
        synchronized (this) {
            requestsNumber++;
            equationList.add(eqReceived);
            System.out.println("The total number of expressions received is: " + requestsNumber);
            System.out.println("-----------OPERATION-----------");
            eqReceived.accept(new NodeDisplayVisitor());
            System.out.println("");
        }
    }
}
