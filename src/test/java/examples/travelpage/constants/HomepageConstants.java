package examples.travelpage.constants;

import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.core.namespaces.factory.LazyLocatorFactory;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;
import com.github.karsaii.framework.selenium.constants.SelectorStrategyNameConstants;
import com.github.karsaii.framework.selenium.namespaces.lazy.LazyElementFactory;
import com.github.karsaii.framework.selenium.namespaces.lazy.LazyElementParameterMapFactory;
import com.github.karsaii.framework.selenium.namespaces.lazy.LazyFilteredElementParametersFactory;
import com.github.karsaii.framework.selenium.namespaces.utilities.SeleniumUtilities;
import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

public abstract class HomepageConstants {
    public static final String NAME = "Homepage - ";

    public static final String LOGO_NAME = NAME + "Logo";
    public static final String DESTINATION_FIELD_NAME = NAME + "Destination field";


    public static final String LOGO_LOCATOR = "img[alt=*' logo']";
    public static final String DESTINATION_FIELD_LOCATOR = "#location-field-destination";
    public static final String DESTINATION_FIELD_LOCATOR_2 = "hotel-destination-hp-hotel";

    public static final LazyLocator DEST_LAZY_1 = LazyLocatorFactory.get(DESTINATION_FIELD_LOCATOR, SelectorStrategyNameConstants.CSS_SELECTOR);
    public static final LazyLocator DEST_LAZY_2 = LazyLocatorFactory.get(DESTINATION_FIELD_LOCATOR_2, SelectorStrategyNameConstants.ID);
    public static final LazyLocator FALSE_DEST = LazyLocatorFactory.get("x" + DESTINATION_FIELD_LOCATOR_2, SelectorStrategyNameConstants.ID);

    public static final LazyElement LOGO = LazyElementFactory.getWithFilterParameters(LOGO_NAME, LazyLocatorFactory.get(LOGO_LOCATOR, SelectorStrategyNameConstants.CSS_SELECTOR));


    /*public static final LazyElement DESTINATION_FIELD_COMPLEX = LazyElementFactory.getWithDefaultValidator(
        DESTINATION_FIELD_NAME,
        LazyElementParameterMapFactory.getWith(
            Map.ofEntries(
                entry(SelectorStrategyNameConstants.CSS_SELECTOR, LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(false, LazyLocatorFactory.get(DESTINATION_FIELD_LOCATOR, SelectorStrategyNameConstants.CSS_SELECTOR))),
                    entry(SelectorStrategyNameConstants.ID+"-3", LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(false, LazyLocatorFactory.get("y" + DESTINATION_FIELD_LOCATOR_2, SelectorStrategyNameConstants.ID))),
                    entry(SelectorStrategyNameConstants.ID+"-2", LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(false, LazyLocatorFactory.get("x" + DESTINATION_FIELD_LOCATOR_2, SelectorStrategyNameConstants.ID))),
                    entry(SelectorStrategyNameConstants.ID, LazyFilteredElementParametersFactory.getWithFilterParametersAndLocator(false, LazyLocatorFactory.get(DESTINATION_FIELD_LOCATOR_2, SelectorStrategyNameConstants.ID)))
            )
        )
    );*/

    public static final LazyElement DESTINATION_FIELD_COMPLEX = LazyElementFactory.getWithFilterParameters(
        DESTINATION_FIELD_NAME,
        new LazyLocatorList(Arrays.asList(FALSE_DEST, FALSE_DEST, FALSE_DEST, DEST_LAZY_1, DEST_LAZY_2))
    );

    public static final LazyElement DESTINATION_FIELD = LazyElementFactory.getWithFilterParameters(DESTINATION_FIELD_NAME, LazyLocatorFactory.get(DESTINATION_FIELD_LOCATOR_2, SelectorStrategyNameConstants.ID));

    public static final Map<String, LazyElement> ELEMENT_MAP = Collections.unmodifiableMap(
        new LinkedHashMap<>(
            Map.ofEntries(
                entry(LOGO_NAME, LOGO),
                entry(DESTINATION_FIELD_NAME, DESTINATION_FIELD_COMPLEX)
            )
        )
    );
}
