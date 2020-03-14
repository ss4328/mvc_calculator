package com.shiv;

public class Model {

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
        return res;
    }
}
