package ferzz;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(int width, int height){
        Dimension minFrameSize = new Dimension(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(minFrameSize);
        setTitle("Functions Application");
        repaint();
    }
}
