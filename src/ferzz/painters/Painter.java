package ferzz.painters;

import ferzz.convertion.ConvertPlane;

import java.awt.*;

public abstract class Painter {
    protected ConvertPlane plane;
    public Painter(ConvertPlane p){
        plane = p;
    }
    public abstract void draw(Graphics graphics);
}
