package ru.geekbrains.lesson8;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by geekbrains on 11/18/20
 */
public class MouseFrame extends JFrame {

    public MouseFrame() {
        setTitle("Работа с мышью");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);

        JPanel panel = new JPanel();
        add(panel);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int globalX = e.getXOnScreen();
                int globalY = e.getYOnScreen();
                System.out.println("Позиция мыши: (" + globalX + ", " + globalY + ')');
                openNewWindowInCoords(globalX, globalY);
            }
        });


        setVisible(true);
    }

    private void openNewWindowInCoords(int x, int y) {
        MouseFrame mouseFrame = new MouseFrame();
        mouseFrame.setBounds(x, y, 100, 100);
        mouseFrame.setVisible(true);
    }

    public static void main(String[] args) {
        MouseFrame mouseFrame = new MouseFrame();
    }
}