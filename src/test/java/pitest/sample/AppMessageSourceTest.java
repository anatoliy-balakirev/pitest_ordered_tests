package pitest.sample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pitest.sample.AppMessageSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
    classes = {AppMessageSource.class},
    initializers = ConfigFileApplicationContextInitializer.class)
public class AppMessageSourceTest {
    private static final String TEST_KEY = "test";
    private static final String TEST_EN = "test en";
    private static final String TEST_DE = "test de";
    private static final String TEST_WRONG = "wrong";

    @Autowired
    private AppMessageSource messageSource;

    @Test
    void testGetMessageSuccess() {
        assertEquals(TEST_EN, messageSource.getMessage(TEST_KEY, "en"));
        assertEquals(TEST_DE, messageSource.getMessage(TEST_KEY, "de"));
    }

    @Test
    void testGetMessageDefaultLanguage() {
        assertEquals(TEST_EN, messageSource.getMessage(TEST_KEY, "es"));
    }

    @Test
    void testGetMessageNotExist() {
        assertEquals(TEST_WRONG, messageSource.getMessage(TEST_WRONG, "de"));
    }
}
