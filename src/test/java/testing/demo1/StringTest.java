package testing.demo1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Примеры тестов c аннотациями
 */
public class StringTest {

    //тестим что под индексом 0 стоит символ 'H'
    @Test
    public void substring_should_count_chars_from_0() {
        assertEquals('H', "Hello".charAt(0));
    }

    //тестим что будет брошено исключение
    @Test
    public void substring_should_throw_exception_for_invalid_index() {
        try {
            "".substring(1);
            fail("Expected exception not throw");
        } catch (Exception e) {
            //pass
        }
    }

    //тестим что метод выполнится за 1сек
    @Test
    @Timeout(value = 1_000L, unit = TimeUnit.MILLISECONDS)
    public void should_finish_in_1_second() throws Exception {
        Thread.sleep(2_000L);
    }
}
