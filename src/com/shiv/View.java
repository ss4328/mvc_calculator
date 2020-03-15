package com.shiv;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

    private JTextField displayBar;
    JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bd, bm, beq, bCan;
    //variables for storing the operations, and answer
    String s0, operation, s2;
    String res;

    Controller controller;

    View(){
        displayBar = new JTextField(16);
        reset();
        res="";
    }

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

        bCan.setBounds(40,310,50,40);
    }

    public void setResult(String res) {
        this.displayBar.setText(res);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Integer val = 0;
        String buttonVal = e.getActionCommand();
        // if the value is a number
        if ((buttonVal.charAt(0) >= '0' && buttonVal.charAt(0) <= '9')) {
            // if operand is present then add to second no
            if(!res.equals("")){
                reset();
                res="";
            }

            if (!operation.equals(""))
                s2 = s2 + buttonVal;
            else
                s0 = s0 + buttonVal;

            // set the value of text
            displayBar.setText(s0 + operation + s2);
        }
        else if (buttonVal.charAt(0) == 'C') {
            // clear the one letter
            reset();

            // set the value of text
            displayBar.setText("");
        }
        else if (buttonVal.charAt(0) == '=') {
            // if s1,op,s2 are filled, only then compute
            if(!s0.equals("") && !operation.equals("") && !s2.equals("")){
                compute();
                controller.sendMessageToServer();
            }
            else if(!s0.equals("") &&operation.equals("")){
                boolean reset = showResetDialog();
                //user chooses not to reset
                if(reset){
                    operation = buttonVal;
                    displayBar.setText(s0 + operation + s2);
                }
            }
        }

        //operation key pressed
        else {
            //chain operation case
            if(!s0.equals("") && !operation.equals("") && !s2.equals("")){
                compute();
                operation = buttonVal;
                s0=displayBar.getText();
                s2="";
                displayBar.setText(s0 + operation + s2);
                res="";
            }

            else if (operation.equals("") && s2.equals("")){
                operation = buttonVal;
                // set the value of text
                displayBar.setText(s0 + operation + s2);
            }
            else if (!operation.equals("")){
                boolean reset = showResetDialog();
                //user chooses not to reset
                if(reset){
                    operation = buttonVal;
                    displayBar.setText(s0 + operation + s2);
                }
                //user chooses to reset
                else{
                    //condition handled inside dialog box
                }
            }
                // else evaluate
            else {
                compute();
                // place the operator
                operation = buttonVal;
                // set the value of text
                displayBar.setText(s0 + operation + s2);
            }
        }
    }

    public void compute(){
        if (operation.equals("+"))
            controller.handleUserInput(s0,"+",s2);
        else if (operation.equals("-"))
            controller.handleUserInput(s0,"-",s2);
        else if (operation.equals("/"))
            controller.handleUserInput(s0,"/",s2);
        else
            controller.handleUserInput(s0,"*",s2);
    }

   public boolean showResetDialog(){
        boolean res = true;
       int dialogButton = JOptionPane.YES_NO_OPTION;
       if (JOptionPane.showConfirmDialog(null, "Do you want to accept new operation? Choosing No will reset the calculator", "WARNING",
               JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
           // yes option
       } else {
           // no option
           s0= operation =s2="";
           displayBar.setText("");
           res=false;
       }
       return res;
   }

    public void reset(){
        s0= operation =s2="";
        displayBar.setText("");
    }
}
