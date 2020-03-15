package com.shiv;

import com.shiv.Client.Client;
import com.shiv.VisitorLib.Equation;
import com.shiv.VisitorLib.Node;
import com.shiv.VisitorLib.Number;
import com.shiv.VisitorLib.Operation;

import java.util.ArrayList;

public class Model {
    ArrayList<Node> eqNodes;
    ArrayList<Node> operators;
    ArrayList<Node> operands;
    String equation;
    public Integer index;


    public Model() {
        flushLists();
        index =0;
    }
    public int calculate(int i1, String op, int i2) {

        int res;
        switch (op) {
            case "+":
                res = i1 + i2;
                break;
            case "-":
                res = i1 - i2;
                break;
            case "*":
                res = i1*i2;
                break;
            case "/":
                if(i2!=0){
                    res = i1/i2;
                }
                else{
                    throw new RuntimeException("Division by 0");
                }
                break;
            default:
                throw new RuntimeException("impossible operator");
        }

        sendMessageToServer(i1,op,i2,res);

        return res;
    }

    public void sendMessageToServer(int i1, String op, int i2,int res){
        try{
            Node i1Node = new Number(Integer.toString(i1));
            Node i2Node = new Number(Integer.toString(i2));
            Node operNode = new Operation(op);

            eqNodes.add(i1Node);
            eqNodes.add(operNode);
            eqNodes.add(i2Node);
            equation = Integer.toString(i1) + op + Integer.toString(i2) + "=" + Integer.toString(res) ;
            operators.add(operNode);
            operands.add(i1Node);
            operands.add(i2Node);

            Equation eq = new Equation(eqNodes,operands,operators,equation);
            Client c = new Client();
            c.sendToServer(eq);
            flushLists();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void flushLists(){
        eqNodes=new ArrayList<Node>();
        operators=new ArrayList<Node>();
        operands=new ArrayList<Node>();
    }

    public void sendMessageToServer(){

    }
}
