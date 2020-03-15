package com.shiv.VisitorLib;

import java.io.Serializable;

public class Operation implements Node, Serializable {
    private static final long serialVersionUID = 1234567892;
    String val;

    public Operation(String stringPassed){
        val = stringPassed;
    }

    @Override
    public void accept(NodeVisitor nodeVisitorPassed) {
        nodeVisitorPassed.visit(this);
    }
}
