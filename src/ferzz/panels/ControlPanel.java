package ferzz.panels;

import ferzz.panels.listeners.IBaseListener;
import ferzz.panels.listeners.IResizedListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class ControlPanel extends JPanel {
    private final ArrayList<IBaseListener> listeners = new ArrayList<>();
    public final SpinnerNumberModel xMinModel = new SpinnerNumberModel(-5.0, -5.1, 4.9, 0.1);
    public final SpinnerNumberModel xMaxModel = new SpinnerNumberModel(5.0, -4.9, 5.1, 0.1);
    public final SpinnerNumberModel yMinModel = new SpinnerNumberModel(-5.0, -5.1, 4.9, 0.1);
    public final SpinnerNumberModel yMaxModel = new SpinnerNumberModel(5.0, -4.9, 5.1, 0.1);
    public final JSpinner xMinSpinner = new JSpinner(xMinModel);
    public final JSpinner xMaxSpinner = new JSpinner(xMaxModel);
    public final JSpinner yMinSpinner = new JSpinner(yMinModel);
    public final JSpinner yMaxSpinner = new JSpinner(yMaxModel);
    public final JLabel xMinLabel = new JLabel("X_min");
    public final JLabel xMaxLabel = new JLabel("X_max");
    public final JLabel yMinLabel = new JLabel("Y_min");
    public final JLabel yMaxLabel = new JLabel("Y_max");
    public ControlPanel() {
        setBackground(Color.GRAY.brighter());
        setBorder(new EtchedBorder());
        handleSpinnersEvent();
        configureAndSetLayout();
    }
    public void addResizedListener(IResizedListener listener){
        listeners.add(listener);
    }//добавляем слушателя
    public double getXMin() {
        return (double) xMinSpinner.getValue();
    }

    public double getXMax() {
        return (double) xMaxSpinner.getValue();
    }

    public double getYMin() {
        return (double) yMinSpinner.getValue();
    }

    public double getYMax() {
        return (double) yMaxSpinner.getValue();
    }

    private void executeResizedListeners(){
        listeners.forEach(l -> {
            if(l instanceof IResizedListener){
                ((IResizedListener) l).resize();
            }
        });
    }
    private void handleSpinnersEvent(){
        xMinSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                xMinModel.setMinimum((double) xMinSpinner.getValue() - 1.);
                xMaxModel.setMinimum((double) xMinSpinner.getValue() + 0.1);
                executeResizedListeners(); //вызов обработки события у нужных слушателей
            }
        });
        xMaxSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                xMinModel.setMaximum((double) xMaxSpinner.getValue() - 0.1);
                xMaxModel.setMaximum((double) xMaxSpinner.getValue() + 1.);
                executeResizedListeners();
            }
        });
        yMinSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                yMinModel.setMinimum((double) yMinSpinner.getValue() - 1.);
                yMaxModel.setMinimum((double) yMinSpinner.getValue() + 0.1);
                executeResizedListeners();
            }
        });
        yMaxSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                yMinModel.setMaximum((double) yMaxSpinner.getValue() - 0.1);
                yMaxModel.setMaximum((double) yMaxSpinner.getValue() + 1.);
                executeResizedListeners();
            }
        });
    }

    private void configureAndSetLayout(){
        GroupLayout gl = new GroupLayout(this);
        Dimension MIN_COMPONENT_SIZE = new Dimension(40, 20);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addComponent(xMinLabel, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(yMinLabel, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addComponent(xMinSpinner, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(yMinSpinner, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(20)
                .addGroup(gl.createParallelGroup()
                        .addComponent(xMaxLabel, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(yMaxLabel, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addComponent(xMaxSpinner, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(yMaxSpinner, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addGap(5)
                        .addComponent(xMinLabel, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(xMinSpinner, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(20)
                        .addComponent(xMaxLabel, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(xMaxSpinner, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(gl.createParallelGroup()
                        .addGap(5)
                        .addComponent(yMinLabel, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(yMinSpinner, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(20)
                        .addComponent(yMaxLabel, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(yMaxSpinner, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
        );
        setLayout(gl);
    }
}