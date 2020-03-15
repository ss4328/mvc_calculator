package com.shiv;

import com.shiv.VisitorLib.Node;
import com.shiv.VisitorLib.Number;
import com.shiv.VisitorLib.Operation;

import java.util.ArrayList;

public class Model {
    ArrayList<Node> eqNodes;
    public Integer index;


    public Model() {
        eqNodes=new ArrayList<Node>();
        index =0;
    }
    public int calculate(int i1, String op, int i2) {
        Node i1Node = new Number(Integer.toString(i1),index);
        Node i2Node = new Number(Integer.toString(i2),index);
        Node oper = new Operation(op,index);

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
        return res;
    }

    public void sendMessageToServer(){

    }
}
