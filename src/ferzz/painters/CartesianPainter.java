package ferzz.painters;

import ferzz.convertion.ConvertPlane;
import ferzz.convertion.Converter;

import java.awt.*;

public class CartesianPainter extends Painter{
    public CartesianPainter(ConvertPlane plane) {
        super(plane);
    }

    @Override
    public void draw(Graphics graphics) {
        drawAxes(graphics);
        drawTicks(graphics);
        drawNumbers(graphics);
    }

    private void drawAxes(Graphics graphics){
        var axesColor = Color.GRAY;
        if (graphics != null) {
            graphics.setColor(axesColor);
            //ось X
            graphics.drawLine(Converter.xCrt2Scr(plane.getXMin(), plane), Converter.yCrt2Scr(0, plane),
                    Converter.xCrt2Scr(plane.getXMax(), plane), Converter.yCrt2Scr(0, plane));
            //ось Y
            graphics.drawLine(Converter.xCrt2Scr(0, plane), Converter.yCrt2Scr(plane.getYMax(), plane),
                    Converter.xCrt2Scr(0, plane), Converter.yCrt2Scr(plane.getYMin(), plane));
        }
    }

    private void drawTicks(Graphics graphics){
        if(graphics == null){
            return;
        }
        int df = 2;//смещение
        drawXTicks(graphics, df);
        drawYTicks(graphics, df);
    }

    private void drawXTicks(Graphics graphics, int bias){
        var y0 = Converter.yCrt2Scr(0., plane);
        for (long i = (int) (plane.getXMin() * 10); i < (int) (plane.getXMax() * 10); i++) {
            var h = 2;
            if (i % 10 == 0) {
                graphics.setColor(Color.RED);
            } else if (i % 5 == 0) {
                h = 1;
                graphics.setColor(Color.BLUE);
            } else {
                graphics.setColor(Color.DARK_GRAY);
                h = 0;
            }
            graphics.drawLine(Converter.xCrt2Scr(i / 10., plane), y0 + bias + h,
                    Converter.xCrt2Scr(i / 10., plane), y0 - bias - h);
        }
    }

    private void drawYTicks(Graphics graphics, int bias){
        var x0 = Converter.xCrt2Scr(0., plane);
        for (long i = (int) (plane.getYMin() * 10); i < (int) (plane.getYMax() * 10); i++) {
            var h = 2;
            if (i % 10 == 0) {
                graphics.setColor(Color.RED);
            } else if (i % 5 == 0) {
                h = 1;
                graphics.setColor(Color.BLUE);
            } else {
                graphics.setColor(Color.DARK_GRAY);
                h = 0;
            }
            graphics.drawLine(x0 + bias + h, Converter.yCrt2Scr(i / 10., plane),
                    x0 - bias - h, Converter.yCrt2Scr(i / 10., plane));
        }
    }

    private void drawNumbers(Graphics graphics){
        if(graphics == null){
            return;
        }
        graphics.setColor(Color.MAGENTA);
        drawXNumbers(graphics);
        drawYNumbers(graphics);
    }

    private void drawXNumbers(Graphics graphics){
        var y0 = Converter.yCrt2Scr(0., plane);
        for (long i = (int) (plane.getXMin() * 10); i < (int) (plane.getXMax() * 10); i++) {
            if (i == 0 || i % 5 != 0)
                continue;
            var h = -10;
            if (i > 0)
                h = 20;
            var p = i > 0 ? -6 : -10;
            graphics.drawString(String.valueOf(i / 10.), Converter.xCrt2Scr(i / 10., plane) + p, y0 + h);
        }
    }

    private void drawYNumbers(Graphics graphics){
        var x0 = Converter.xCrt2Scr(0., plane);
        for (long i = (int) (plane.getYMin() * 10); i < (int) (plane.getYMax() * 10); i++) {
            if (i == 0 || i % 5 != 0)
                continue;
            var h = -30;
            if (i > 0)
                h = 10;
            graphics.drawString(String.valueOf(i / 10.), x0 + h, Converter.yCrt2Scr(i / 10., plane) + 4);
        }
    }
}
