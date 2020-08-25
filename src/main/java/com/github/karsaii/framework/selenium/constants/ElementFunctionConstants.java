package com.github.karsaii.framework.selenium.constants;

import com.github.karsaii.core.extensions.namespaces.CardinalitiesFunctions;
import com.github.karsaii.framework.selenium.namespaces.Driver;
import com.github.karsaii.framework.selenium.namespaces.ExecutionCore;
import com.github.karsaii.framework.selenium.namespaces.validators.SeleniumDataValidators;
import com.github.karsaii.framework.selenium.records.element.is.regular.ElementParameterizedValueParameters;
import com.github.karsaii.framework.selenium.records.element.is.regular.ElementConditionParameters;
import com.github.karsaii.framework.selenium.records.element.is.regular.ElementStringValueParameters;

public abstract class ElementFunctionConstants {
    public static final ElementConditionParameters<Boolean> PRESENT = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.PRESENT_DATA, SeleniumDataValidators::isValidLazyElement, CardinalitiesFunctions::noopBoolean);
    public static final ElementConditionParameters<Boolean> DISPLAYED = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.DISPLAYED_DATA, Driver::invokeElementDisplayed, CardinalitiesFunctions::noopBoolean);
    public static final ElementConditionParameters<Boolean> ENABLED = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.ENABLED_DATA, Driver::invokeElementEnabled, CardinalitiesFunctions::noopBoolean);
    public static final ElementConditionParameters<Boolean> CLICKABLE = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.CLICKABLE_DATA, Driver::invokeElementClickable, CardinalitiesFunctions::noopBoolean);
    public static final ElementConditionParameters<Boolean> SELECTED = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.SELECTED_DATA, Driver::invokeElementSelected, CardinalitiesFunctions::noopBoolean);

    public static final ElementConditionParameters<Boolean> ABSENT = new ElementConditionParameters<>(ExecutionCore::nonNullChain, ElementFormatDataConstants.ABSENT_DATA, SeleniumDataValidators::isNotNull, CardinalitiesFunctions::invertBoolean);
    public static final ElementConditionParameters<Boolean> HIDDEN = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.HIDDEN_DATA, Driver::invokeElementDisplayed, CardinalitiesFunctions::invertBoolean);
    public static final ElementConditionParameters<Boolean> DISABLED = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.DISABLED_DATA, Driver::invokeElementEnabled, CardinalitiesFunctions::invertBoolean);
    public static final ElementConditionParameters<Boolean> UNCLICKABLE = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.UNCLICKABLE_DATA, Driver::invokeElementClickable, CardinalitiesFunctions::invertBoolean);
    public static final ElementConditionParameters<Boolean> UNSELECTED = new ElementConditionParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.UNSELECTED_DATA, Driver::invokeElementSelected, CardinalitiesFunctions::invertBoolean);

    public static final ElementStringValueParameters<String> TEXT = new ElementStringValueParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.TEXT_DATA, Driver::invokeGetElementText);
    public static final ElementStringValueParameters<String> TAGNAME = new ElementStringValueParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.TAGNAME_DATA, Driver::invokeGetElementText);
    public static final ElementParameterizedValueParameters<String> ATTRIBUTE = new ElementParameterizedValueParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.ATTRIBUTE_DATA, Driver::invokeGetElementAttribute);
    public static final ElementParameterizedValueParameters<String> VALUE_ATTRIBUTE = new ElementParameterizedValueParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.VALUE_ATTRIBUTE_DATA, Driver::invokeGetElementAttribute);
    public static final ElementParameterizedValueParameters<String> CSS_VALUE = new ElementParameterizedValueParameters<>(ExecutionCore::validChain, ElementFormatDataConstants.CSS_VALUE_DATA, Driver::invokeGetElementCssValue);
}
