package com.shiv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

    JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bd, bm, beq, bCan;
    String s0, s1, s2;

    Controller controller;
    //JLabel result;

    View(){
        s0=s1=s2="";
    }

    static JTextField displayBar;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void show() {
        JFrame frame = new JFrame("MVC Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayBar = new JTextField(16);
        displayBar.setEditable(false);

        // create number buttons
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        //equal button
        bCan = new JButton("C");

        // create operator buttons
        ba = new JButton("+");
        bs = new JButton("-");
        bd = new JButton("/");
        bm = new JButton("*");
        beq = new JButton("=");

        setBounds();

        frame.add(displayBar);

        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(b5);
        frame.add(b6);
        frame.add(b7);
        frame.add(b8);
        frame.add(b9);
        frame.add(b0);

        frame.add(ba);
        frame.add(bs);
        frame.add(bm);
        frame.add(bd);
        frame.add(beq);
        frame.add(bCan);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(350,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        bm.addActionListener(this);
        bd.addActionListener(this);
        bs.addActionListener(this);
        ba.addActionListener(this);
        b9.addActionListener(this);
        b8.addActionListener(this);
        b7.addActionListener(this);
        b6.addActionListener(this);
        b5.addActionListener(this);
        b4.addActionListener(this);
        b3.addActionListener(this);
        b2.addActionListener(this);
        b1.addActionListener(this);
        b0.addActionListener(this);
        beq.addActionListener(this);
        bCan.addActionListener(this);
    }

    public void setBounds(){
        displayBar.setBounds(30,40,280,30);
        b7.setBounds(40,100,50,40);
        b8.setBounds(110,100,50,40);
        b9.setBounds(180,100,50,40);
        bd.setBounds(250,100,50,40);

        b4.setBounds(40,170,50,40);
        b5.setBounds(110,170,50,40);
        b6.setBounds(180,170,50,40);
        bm.setBounds(250,170,50,40);

        b1.setBounds(40,240,50,40);
        b2.setBounds(110,240,50,40);
        b3.setBounds(180,240,50,40);
        bs.setBounds(250,240,50,40);

        b0.setBounds(110,310,50,40);
        beq.setBounds(180,310,50,40);
        ba.setBounds(250,310,50,40);

        bCan.setBounds(60,380,100,40);
    }

    public void setResult(String res) {
        this.displayBar.setText(res);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Integer val = 0;
        String s = e.getActionCommand();
        // if the value is a number
        if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
            // if operand is present then add to second no
            if (!s1.equals(""))
                s2 = s2 + s;
            else
                s0 = s0 + s;

            // set the value of text
            displayBar.setText(s0 + s1 + s2);
        }

        else if (s.charAt(0) == 'C') {
            // clear the one letter
            s0 = s1 = s2 = "";

            // set the value of text
            displayBar.setText("");
        }
        else if (s.charAt(0) == '=') {

            double te;

            // store the value in 1st
            if (s1.equals("+"))
                controller.handleUserInput(s0,"+",s2);
            else if (s1.equals("-"))
                controller.handleUserInput(s0,"-",s2);
            else if (s1.equals("/"))
                controller.handleUserInput(s0,"/",s2);
            else
                controller.handleUserInput(s0,"*",s2);

            // set the value of text
            //displayBar.setText(s0 + s1 + s2 + "=" + te);

            // convert it to string
            //s0 = Double.toString(te);

            s0= s1 = s2 = "";
        }
        else {
            // if there was no operand
            if (s1.equals("") || s2.equals(""))
                s1 = s;
                // else evaluate
            else {
                double te;

                // store the value in 1st
                if (s1.equals("+"))
                    controller.handleUserInput(s0,"+",s2);
                else if (s1.equals("-"))
                    controller.handleUserInput(s0,"-",s2);
                else if (s1.equals("/"))
                    controller.handleUserInput(s0,"/",s2);
                else
                    controller.handleUserInput(s0,"*",s2);

                // convert it to string
                //s0 = Double.toString(te);

                // place the operator
                s1 = s;

                // make the operand blank
                s2 = "";
            }

            // set the value of text
            displayBar.setText(s0 + s1 + s2);
        }


//        if(e.getSource()==this.b0 || e.getSource()==this.b1 ||e.getSource()==this.b2 ||e.getSource()==this.b3 ||e.getSource()==this.b4 ||e.getSource()==this.b5 ||e.getSource()==this.b6 ||e.getSource()==this.b7 ||e.getSource()==this.b8 ||e.getSource()==this.b8){
//            val = e.getSource().
//        }

        //get input 1
//        String s1 = input1.getText();
//        //get op
//        String op = operand.getItemAt(operand.getSelectedIndex());
//        //get input 2
//        String s2 = input2.getText();
//        //call controller's handle method
//        controller.handleUserInput(s1, op, s2);
    }


}
