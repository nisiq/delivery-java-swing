package components;
import javax.swing.*;
import java.awt.*;

public class Botao extends JButton {
    public Botao(String text){
        super(text);
        setBackground(Color.decode("#8B0000"));
        setForeground(Color.WHITE);
    }
}