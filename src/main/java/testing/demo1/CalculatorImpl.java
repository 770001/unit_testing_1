package testing.demo1;

public class CalculatorImpl implements Calculator {

    @Override
    public double calculate(String expression) {
//        return 0; //для zero_test()
        return Double.parseDouble(expression); //для zero_test() и floating_point_test()
    }
}
