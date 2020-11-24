package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {
    public MyWindow() {
        setTitle("Чат с самим собой");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);

        JButton ok = new JButton("send");

        setLayout(new BorderLayout());

        JTextField text = new JTextField();
        JTextArea bigField = new JTextArea();
        bigField.setLineWrap(true);
        bigField.setWrapStyleWord(true);

        add(text, BorderLayout.NORTH);
        add(new JScrollPane(bigField), BorderLayout.CENTER);

        add(ok, BorderLayout.SOUTH);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bigField.setText(bigField.getText() + '\n' + "-> " + text.getText());
                text.setText("");
            }
        });

        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bigField.setText(bigField.getText() + '\n' + "-> " + text.getText());
                text.setText("");
            }
        });

        setVisible(true);
    }


    public static void main(String[] args) {
        MyWindow myWindow = new MyWindow();
    }

}
