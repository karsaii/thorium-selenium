package com.github.karsaii.framework.selenium.namespaces.repositories;

import com.github.karsaii.framework.selenium.enums.SeleniumTypeKey;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.records.SeleniumTypedEnumKeyData;

import java.util.Map;

public interface FunctionRepository {
    static <T> DriverFunction<T> get(Map<SeleniumTypeKey, DriverFunction<?>> functionMap, SeleniumTypedEnumKeyData<T> keyData) {
        return (DriverFunction<T>) functionMap.get(keyData.key);
    }
}
