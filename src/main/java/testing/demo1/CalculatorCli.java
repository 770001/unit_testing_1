package testing.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.function.DoubleConsumer;
import java.util.stream.Stream;

/**
 * Коммандный интерфейс калькулятора
 * который мы будем тестить
 * (сам калькулятор может быть еще не написан/не готов).
 * Заглушку калькулятора лего подставляем через конструктор.
 */
public class CalculatorCli {

    private final Calculator calculator;

    public CalculatorCli(Calculator calculator) {
        this.calculator = calculator;
    }

    public void runInteractiveSession(Reader reader) throws Exception {
        runInteractiveSession(reader, System.out::println); //ссылка на метод - вывод в консоль
    }

    public void runInteractiveSession(Reader reader, DoubleConsumer resultConsumer) {
        new BufferedReader(reader).lines()
                .flatMap((s) -> Stream.of(s.split(";"))) //разрешаем вводить несколько значений разделенных ;
                .filter((s) -> !s.trim().isEmpty()) //пропускаем пустые строчки
                .mapToDouble(calculator::calculate) //скармлваем калькулятору
                .forEach(resultConsumer) //печатаем в консоль
        ;
    }

    public static void main(String[] args) throws Exception {
        CalculatorCli calculatorCli = new CalculatorCli(new CalculatorImpl());
        try (Reader reader = new InputStreamReader(System.in)) {
            calculatorCli.runInteractiveSession(reader);
        }
    }
}
