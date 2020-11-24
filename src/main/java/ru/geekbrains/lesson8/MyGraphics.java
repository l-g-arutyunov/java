package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;

public class MyGraphics extends JPanel {
    @Override
    public void paint(Graphics graphics) {
        graphics.drawLine(10, 10, 200, 300);
        graphics.drawRect(30, 30, 100, 100);
        graphics.fillRect(100, 100, 60, 60);

        Color myColor = new Color(67, 99, 133);
        graphics.setColor(myColor);

        graphics.drawOval(300, 200, 100, 50);

        Color myNewColor = new Color(255, 0, 0);
        graphics.setColor(myNewColor);

        graphics.fillOval(450, 350, 100, 50);


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Welcome to Duke Nukem");
        frame.getContentPane().add(new MyGraphics());
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
