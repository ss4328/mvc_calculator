package com.shiv;

import com.shiv.VisitorLib.Equation;
import com.shiv.VisitorLib.Node;

public abstract class State {
    public String value;
    public State parent;
    public Equation s1 = null;
    public Equation s2 = null;
    public Equation operator = null;

    State(){

    }

    public State(State parent) {
        value="";
        this.parent = parent;
        // if parent is not null, assign parent; check final state is not reached
        if (parent != null && !(parent instanceof Node)) {
            this.s1 = parent.s1;
            this.s2 = parent.s2;
            this.operator = parent.operator;
        }
    }

    public Equation getS1() {
        return s1;
    }
    public Equation getS2(){
        return s2;
    }
    public Equation getOperator(){
        return operator;
    }
}


