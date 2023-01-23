package ferzz;

import ferzz.convertion.ConvertPlane;
import ferzz.functions.ExplicitFunction;
import ferzz.functions.ParametricFunction;
import ferzz.painters.CartesianPainter;
import ferzz.painters.FunctionPainter;
import ferzz.panels.ControlPanel;
import ferzz.panels.GraphicsPanel;
import ferzz.panels.listeners.IResizedListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame extends JFrame {
    public final GraphicsPanel graphicsPanel = new GraphicsPanel();
    public final ControlPanel controlPanel = new ControlPanel();
    public final ConvertPlane plane;

    public MainFrame(int width, int height){
        Dimension minFrameSize = new Dimension(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(minFrameSize);
        setTitle("Functions Application");
        configureAndSetLayout();
        plane = initAndGetPlane(graphicsPanel.getWidth(), graphicsPanel.getHeight());
        handleFrameEvents();
        handleGraphicsPanelEvents();
        handlerControlPanelEvents();
        createAndAddPainters();
        repaint();
    }

    private void configureAndSetLayout(){
        GroupLayout gl = new GroupLayout(getContentPane());
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addComponent(graphicsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(5)
        );
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addComponent(graphicsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(5)
        );
        setLayout(gl);
        pack();
    }

    private void handleFrameEvents(){
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                updatePlaneDimension(graphicsPanel.getWidth(), graphicsPanel.getHeight());
                graphicsPanel.repaint();
            }
        });
    }

    private void handleGraphicsPanelEvents(){
        graphicsPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                updatePlaneDimension(graphicsPanel.getWidth(), graphicsPanel.getHeight());
                graphicsPanel.repaint();
            }
        });
    }

    private void handlerControlPanelEvents(){
        controlPanel.addResizedListener(new IResizedListener() {
            @Override
            public void resize() {
                var xMin = controlPanel.getXMin();
                var xMax = controlPanel.getXMax();
                var yMin = controlPanel.getYMin();
                var yMax = controlPanel.getYMax();
                updatePlaneArgs(xMin, xMax, yMin, yMax);
                graphicsPanel.repaint();
            }
        });
    }

    private ConvertPlane initAndGetPlane(int width, int height){
        return  new ConvertPlane(width, height,
                -5, 5, -5, 5);
    }

    private void updatePlaneDimension(int width, int height){
        plane.setRealWidth(width);
        plane.setRealHeight(height);
    }

    private void updatePlaneArgs(double xMin, double xMax, double yMin, double yMax){
        plane.setXMin(xMin);
        plane.setXMax(xMax);
        plane.setYMin(yMin);
        plane.setYMax(yMax);
    }

    private void createAndAddPainters(){
        graphicsPanel.addPainter(
                new CartesianPainter(plane)
        );
        graphicsPanel.addPainter(
                new FunctionPainter(plane)
                        .setFunction(
                                //new ParametricFunction()
                                new ExplicitFunction()
                        )
        );
    }
}
