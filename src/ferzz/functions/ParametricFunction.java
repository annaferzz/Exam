package ferzz.functions;

import ferzz.functions.interfaces.IParametricFunction;

public class ParametricFunction implements IParametricFunction {
    @Override
    public double getX(double t) {
        return Math.cos(t) + t * Math.sin(t);
    }
    @Override
    public double getY(double t) {
        return Math.sin(t) - t * Math.cos(t);
    }
}
