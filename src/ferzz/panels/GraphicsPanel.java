package ferzz.panels;

import ferzz.painters.Painter;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel {
    private final ArrayList<Painter> painters = new ArrayList<>();

    public GraphicsPanel() {
        setBorder(new EtchedBorder());
        setBackground(Color.WHITE);
    }

    public void addPainter(Painter p) {
        painters.add(p);
    }

    public void removePainter(Painter p) {
        painters.remove(p);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        painters.forEach(painter -> painter.draw(graphics));
    }
}
