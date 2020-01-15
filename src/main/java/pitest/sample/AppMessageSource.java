package pitest.sample;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * ResourceBundleMessageSource to handle our phraseapp messages.
 */
@Service
public class AppMessageSource extends ResourceBundleMessageSource {
    public AppMessageSource() {
        super.setBasenames("app/app");
        setUseCodeAsDefaultMessage(true);
    }

    public String getMessage(final String key, final String locale) {
        return getMessage(key, null, Locale.forLanguageTag(locale));
    }
}
