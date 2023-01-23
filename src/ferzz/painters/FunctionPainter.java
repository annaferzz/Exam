package ferzz.painters;

import ferzz.convertion.ConvertPlane;
import ferzz.convertion.Converter;
import ferzz.functions.interfaces.IExplicitFunction;
import ferzz.functions.interfaces.IFunction;
import ferzz.functions.interfaces.IParametricFunction;

import java.awt.*;

public class FunctionPainter extends Painter {
    private IFunction functionToPaint;

    public FunctionPainter(ConvertPlane plane) {
        super(plane);
    }

    public FunctionPainter setFunction(IFunction function){
        functionToPaint = function;
        return this;
    }
    @Override
    public void draw(Graphics graphics) {
        var step = 0.001; //шаг сдвига точки
        drawFunction(graphics, step);
    }
    private void drawFunction(Graphics graphics, double step){
        var graphxColor = Color.RED;
        if(graphics == null){
            return;
        }
        if(functionToPaint == null){
            return;
        }
        graphics.setColor(graphxColor);
        if(functionToPaint instanceof IExplicitFunction){//проверка совпадения типов функции
            drawExplicitFunction(graphics, step);
        }
        else if(functionToPaint instanceof IParametricFunction){
            drawParametricFunction(graphics, step);
        }
    }

    private void drawExplicitFunction(Graphics graphics, double step){
        var function = (IExplicitFunction) functionToPaint;
        var x = plane.getXMin();
        var xMax = plane.getXMax();
        while(x < xMax){
            var currentY = function.getY(x);
            var nextY = function.getY(x + step);
            if(isFinite(currentY) && isFinite(nextY)){ //проверка на конечность
                graphics.drawLine(
                        Converter.xCrt2Scr(x, plane), Converter.yCrt2Scr(currentY, plane),
                        Converter.xCrt2Scr(x + step, plane), Converter.yCrt2Scr(nextY, plane)
                );//рисование отрезка между значениями функции в точках x и x + step
            }
            x += step;
        }
    }

    private void drawParametricFunction(Graphics graphics, double step){
        var function = (IParametricFunction) functionToPaint;
        double t = 0.0;
        double maxT = 10.0;
        while(t <= maxT){
            var currX = function.getX(t);
            var currY = function.getY(t);
            var nextX = function.getX(t + step);
            var nextY = function.getY(t + step);
            if (isFinite(currX) && isFinite(nextX) && isFinite(currY) && isFinite(nextY)){
                graphics.drawLine(
                        Converter.xCrt2Scr(currX, plane), Converter.yCrt2Scr(currY, plane),
                        Converter.xCrt2Scr(nextX, plane), Converter.yCrt2Scr(nextY, plane)
                );// рисование отрезка между значениями функций X и Y в точках t и t + step
            }
            t += step;
        }
    }

    private boolean isFinite(double number){
        double infinity = 90000000000000.;//"бесконечность"
        return Math.abs(number) < infinity;
    }
}
