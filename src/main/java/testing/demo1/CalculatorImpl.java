package testing.demo1;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Реалзация калькулятора за счет движка JavaScript который
 * парсит строку expression и выполняет расчет.
 */
public class CalculatorImpl implements Calculator {

    @Override
    public double calculate(String expression) {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("Nashorn");
        try {
            defineMathFunctions(scriptEngine); //помогаем распознавать функции
            return ((Number) scriptEngine.eval(expression)).doubleValue();
        } catch (ScriptException e) {
            throw new IllegalArgumentException("Failed to evaluate expression");
        }
    }

    //говорим что sin это java.lang.Math.sin
    private static void defineMathFunctions(ScriptEngine scriptEngine) throws ScriptException {
        for (String function : new String[] {"sin", "cos", "sqrt"}) {
            scriptEngine.eval("function " + function + "(x) {return Java.type('java.lang.Math')." + function + "(x);}");
        }
    }
 }
