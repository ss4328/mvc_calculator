package com.shiv.VisitorLib;

public class NodeDisplayVisitor implements NodeVisitor {
    @Override
    public void visit(Number num) {
        System.out.println("Operand: " + num.val);
    }

    @Override
    public void visit(Operation op) {
        System.out.println();
        System.out.println("Operator: " + op.val);
    }

    @Override
    public void visit(Equation eq) {
        //System.out.println("Displaying Equation");
    }
}
