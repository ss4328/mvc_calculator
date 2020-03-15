package com.shiv.VisitorLib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Equation implements Node, Serializable {

    private static final long serialVersionUID = 1234567890;

    String equation;
    ArrayList<Node> operators;
    ArrayList<Node> operands;
    ArrayList<Node> eqNodes;

    public Equation(String equationPassed){
        this.equation = equationPassed;
        equationPassed = trimSpaces(equationPassed);
        tokenizeStringEquation(equationPassed);
    }

    public Equation(ArrayList<Node> _eqNodes,ArrayList<Node> _operators,ArrayList<Node> _operands, String _equation){
        this.eqNodes = _eqNodes;
        this.operands = _operands;
        this.operators = _operators;
        equation = _equation;
    }

    public String trimSpaces(String equation){
        String res = equation.replaceAll("\\s","");
        return res;
    }

    public void tokenizeStringEquation(String equation){
        eqNodes = new ArrayList<Node>();
        operators = new ArrayList<Node>();
        operands = new ArrayList<Node>();
        String[] parts = equation.split("\\=");
        String beforeEquals = parts[0];
        StringTokenizer st = new StringTokenizer(beforeEquals, "+-*/", true);
        Integer i=0;
        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if ("+-/*".contains(token)) {
                Node op = new Operation(token);
                eqNodes.add(op);
                operators.add(op);
            } else {
                Node num = new Number(token);
                eqNodes.add(num);
                operands.add(num);
            }
            i++;
        }
        System.out.println("Operators:" + operators);
        System.out.println("Operands:" + operands);
    }

    @Override
    public void accept(NodeVisitor nodeVisitorPassed) {
        System.out.println("Accepting all nodes");

        for (int i = 0; i < eqNodes.size(); i++) {
            eqNodes.get(i).accept(nodeVisitorPassed);
            nodeVisitorPassed.visit(this);
        }
        System.out.println("Equation: "+ equation);
    }
}

