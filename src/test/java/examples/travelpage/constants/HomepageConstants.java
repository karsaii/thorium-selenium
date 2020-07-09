package examples.travelpage.constants;

import com.github.karsaii.framework.core.namespaces.factory.LazyLocatorFactory;
import com.github.karsaii.framework.selenium.constants.SelectorStrategyNameConstants;
import com.github.karsaii.framework.selenium.namespaces.lazy.LazyElementFactory;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

public abstract class HomepageConstants {
    public static final String NAME = "Homepage - ";

    public static final String LOGO_NAME = NAME + "Logo";
    public static final String DESTINATION_FIELD_NAME = NAME + "Destination field";


    public static final String LOGO_LOCATOR = "img[alt=*' logo']";
    public static final String DESTINATION_FIELD_LOCATOR = "location-field-destination";
    public static final String DESTINATION_FIELD_LOCATOR_2 = "hotel-destination-hp-hotel";


    public static final LazyElement LOGO = LazyElementFactory.getWithFilterParameters(LOGO_NAME, LazyLocatorFactory.get(LOGO_LOCATOR, SelectorStrategyNameConstants.CSS_SELECTOR));
    public static final LazyElement DESTINATION_FIELD = LazyElementFactory.getWithFilterParameters(DESTINATION_FIELD_NAME, LazyLocatorFactory.get(DESTINATION_FIELD_LOCATOR_2, SelectorStrategyNameConstants.ID));

    public static final Map<String, LazyElement> ELEMENT_MAP = Collections.unmodifiableMap(
        new LinkedHashMap<>(
            Map.ofEntries(
                entry(LOGO_NAME, LOGO),
                entry(DESTINATION_FIELD_NAME, DESTINATION_FIELD)
            )
        )
    );
}
