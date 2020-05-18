package testing;

import junit.framework.TestCase;

/**
 * Тест от JUnit3 без аннотаций. Устаревшая версия.
 */
public class StringTestJUnit3 extends TestCase {

    //тестим что будет брошено исключение
    public void testSubstringShouldThrowExceptionForInvalidIndex() {
        try {
            "".substring(1);
            fail("Expected exception not throw");
        } catch (Exception e) {
            //pass
        }
    }
}
