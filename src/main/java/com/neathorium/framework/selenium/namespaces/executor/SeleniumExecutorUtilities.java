package com.neathorium.framework.selenium.namespaces.executor;

import com.neathorium.framework.selenium.namespaces.extensions.boilers.DriverFunction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface SeleniumExecutorUtilities {
    private static DriverFunction<?>[] getStepArray(List<DriverFunction<?>> list) {
        return list.toArray(new DriverFunction<?>[0]);
    }

    static DriverFunction<?>[] getStepArray(DriverFunction<?> step, int amount) {
        return getStepArray(Collections.nCopies(amount, step));
    }

    static DriverFunction<?>[] getStepArray(DriverFunction<?> before, DriverFunction<?> after) {
        return getStepArray(Arrays.asList(before, after));
    }
}
