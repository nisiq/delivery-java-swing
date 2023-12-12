package components;

import javax.swing.*;
import java.awt.*;

public class Janela extends JFrame {
        public Janela(String title) {
            super(title);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 600);
            setLayout(null);
            getContentPane().setBackground(Color.WHITE);
            setLocationRelativeTo(null);
            setResizable(false);
    }

    public void add(ImageIcon iconeAmpliado) {
    }
}