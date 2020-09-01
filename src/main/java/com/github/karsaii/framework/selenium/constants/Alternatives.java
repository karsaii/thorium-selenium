package com.github.karsaii.framework.selenium.constants;

import com.github.karsaii.framework.selenium.namespaces.element.Element;
import com.github.karsaii.framework.selenium.namespaces.element.ElementAlternatives;
import com.github.karsaii.framework.selenium.records.element.regular.ClearData;
import com.github.karsaii.framework.selenium.records.element.regular.ClickData;
import com.github.karsaii.framework.selenium.records.element.ElementFunctionsData;
import com.github.karsaii.framework.selenium.records.element.regular.SendKeysData;
import com.github.karsaii.framework.selenium.records.expectedcondition.regular.ElementAlternative;

public abstract class Alternatives {
    public static final ElementAlternative regular = new ElementAlternative();
    public static final ElementAlternative keyboardClear = new ElementAlternative(
        new ElementFunctionsData(
            new ClickData(Element::click, Element::click, Element::click),
            new ClearData(ElementAlternatives::clearWithSelectAll, ElementAlternatives::clearWithSelectAll, ElementAlternatives::clearWithSelectAll),
            new SendKeysData(Element::sendKeys, Element::sendKeys, Element::sendKeys)
        )
    );

    public static final ElementAlternative clickWithEventDispatcher = new ElementAlternative(
        new ElementFunctionsData(
            new ClickData(ElementAlternatives::clickWithEventDispatcher, ElementAlternatives::clickWithEventDispatcher, ElementAlternatives::clickWithEventDispatcher),
            new ClearData(Element::clear, Element::clear, Element::clear),
            new SendKeysData(Element::sendKeys, Element::sendKeys, Element::sendKeys)
        )
    );
}
