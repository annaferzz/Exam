package ferzz.functions;

import ferzz.functions.interfaces.IExplicitFunction;

public class ExplicitFunction implements IExplicitFunction {
    @Override
    public double getY(double x) {
        return 2 / (x * x + 1);
    }
}
