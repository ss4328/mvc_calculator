package com.shiv.VisitorLib;

public interface NodeVisitor {
    public void visit(Number num);
    public void visit(Operation op);
    public void visit(Equation eq);
}
