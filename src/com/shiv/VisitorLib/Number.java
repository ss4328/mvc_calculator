package com.shiv.VisitorLib;

import java.io.Serializable;

public class Number implements Node, Serializable {
    private static final long serialVersionUID = 1234567891;

    Integer val;

    public Number(String stringPassed) {
        try {
            val = Integer.parseInt(stringPassed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void accept(NodeVisitor nodeVisitorPassed) {
        nodeVisitorPassed.visit(this);
    }
}
