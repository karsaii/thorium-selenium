package com.github.karsaii.framework.selenium.namespaces;

import com.github.karsaii.core.constants.CoreConstants;
import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.namespaces.DataExecutionFunctions;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.selenium.constants.DriverFunctionConstants;
import com.github.karsaii.framework.selenium.constants.validators.SeleniumFormatterConstants;
import com.github.karsaii.framework.selenium.namespaces.extensions.boilers.DriverFunction;
import com.github.karsaii.framework.selenium.namespaces.factories.DriverFunctionFactory;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.karsaii.core.extensions.namespaces.CoreUtilities.areNotNull;
import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNotNull;
import static com.github.karsaii.core.namespaces.DataExecutionFunctions.ifDependency;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.prependMessage;
import static com.github.karsaii.core.namespaces.DataFactoryFunctions.replaceMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface ExecutionCore {
    static <DependencyType, ParameterType, ReturnType> Function<DependencyType, Data<ReturnType>> validChain(Function<DependencyType, Data<ParameterType>> function, Function<Data<ParameterType>, Data<ReturnType>> positive, Data<ReturnType> negative) {
        return DataExecutionFunctions.conditionalChain(CoreFormatter::isInvalidOrFalseMessage, function, positive, negative);
    }

    static <ParameterType, ReturnType> DriverFunction<ReturnType> validChain(DriverFunction<ParameterType> function, Function<Data<ParameterType>, Data<ReturnType>> positive, Data<ReturnType> negative) {
        return DriverFunctionFactory.getFunction(DataExecutionFunctions.validChain(function.get(), positive, negative));
    }

    static <ParameterType, ReturnType> DriverFunction<ReturnType> nonNullChain(DriverFunction<ParameterType> function, Function<Data<ParameterType>, Data<ReturnType>> positive, Data<ReturnType> negative) {
        return DriverFunctionFactory.getFunction(DataExecutionFunctions.conditionalChain(CoreFormatter::isTrueMessage, function, positive, negative));
    }

    private static <T> Data<T> ifDriverAnyWrappedCore(WebDriver driver, String nameof, DriverFunction<T> function) {
        return isNotNull(driver) ? (
            DataExecutionFunctions.ifDependencyAnyCore(nameof, function.apply(driver))
        ) : DataFactoryFunctions.getWithNameAndMessage(null, false, nameof, SeleniumFormatterConstants.DRIVER_WAS_NULL, CoreConstants.EXCEPTION);
    }

    private static <T> Function<WebDriver, Data<T>> ifDriverAnyWrappedCore(String nameof, DriverFunction<T> function) {
        return driver -> ifDriverAnyWrappedCore(driver, nameof, function);
    }

    static <T> DriverFunction<T> ifDriver(String nameof, boolean condition, DriverFunction<T> positive, Data<T> negative) {
        final var lNameof = isBlank(nameof) ? "ifDriver" : nameof;
        return condition && isNotNull(positive) ? (
            DriverFunctionFactory.getFunction(ifDriverAnyWrappedCore(lNameof, positive))
        ) : DriverFunctionFactory.get(DataExecutionFunctions.ifDependencyAnyCore(lNameof, negative));
    }

    static <T> DriverFunction<T> ifDriver(String nameof, DriverFunction<T> positive, Data<T> negative) {
        return DriverFunctionFactory.getFunction(ifDependency(nameof, areNotNull(positive, negative), positive, negative));
    }

    static <T> DriverFunction<T> ifDriver(String nameof, boolean condition, DriverFunction<T> positive, DriverFunction<T> negative) {
        final var lNameof = isBlank(nameof) ? "ifDriver" : nameof;
        final var function = condition && isNotNull(positive) ? positive : negative;
        return DriverFunctionFactory.getFunction(ifDriverAnyWrappedCore(lNameof, function));
    }

    static <T> DriverFunction<T> ifDriver(String nameof, String errorMessage, DriverFunction<T> positive, Data<T> negative) {
        return DriverFunctionFactory.getFunction(ifDependency(nameof, isBlank(errorMessage), positive, replaceMessage(negative, nameof, errorMessage)));
    }

    static <T, U> DriverFunction<U> ifDriver(String nameof, String errorMessage, DriverFunction<T> function, Function<Data<T>, Data<U>> positive, Data<U> negative) {
        return DriverFunctionFactory.getFunction(ifDependency(nameof, isBlank(errorMessage) && areNotNull(function, positive, negative), DriverFunctionFactory.getFunction(function.andThen(positive)), replaceMessage(negative, nameof, errorMessage)));
    }

    static <T, U> DriverFunction<T> ifDriverGuardData(String nameof, Predicate<Data<U>> guard, Function<WebDriver, Data<U>> function, Function<Data<U>, Data<T>> positive, Data<T> negative) {
        return DriverFunctionFactory.getFunction(ifDependency(nameof, areNotNull(guard, function, positive, negative), DataExecutionFunctions.conditionalChain(guard, function, positive, negative), negative));
    }

    static <T, U> DriverFunction<T> ifDriverGuardData(String nameof, Function<WebDriver, Data<U>> function, Function<Data<U>, Data<T>> positive, Data<T> negative) {
        return ifDriverGuardData(nameof, NullableFunctions::isNotNull, function, positive, negative);
    }

    static <T, U> DriverFunction<T> ifDriver(String nameof, boolean status, Function<WebDriver, Data<U>> function, Function<Data<U>, Data<T>> positive, Data<T> negative) {
        return DriverFunctionFactory.getFunction(ifDependency(nameof, status && areNotNull(function, positive, negative), ifDriverGuardData(nameof, function, positive, negative), negative));
    }

    static <T, U> DriverFunction<T> ifDriver(String nameof, Predicate<Data<U>> guard, DriverFunction<U> function, Function<Data<U>, Data<T>> positive, Data<T> negative) {
        return DriverFunctionFactory.getFunction(ifDependency(nameof, areNotNull(guard, function, positive, negative), ifDriverGuardData(nameof, guard, function, positive, negative), negative));
    }

    static <T, U> DriverFunction<U> ifDriverFunction(String nameof, Predicate<Data<?>> guard, DriverFunction<T> function, Function<Data<T>, DriverFunction<U>> positive, Data<U> negative) {
        return DriverFunctionFactory.getFunction(ifDependency(
            nameof,
            areNotNull(guard, function, positive, negative),
            driver -> {
                final var dep = function.apply(driver);
                return guard.test(dep) ? positive.apply(dep).apply(driver) : negative;
            },
            negative
        ));
    }

    static <T, U> Data<U> ifData(String nameof, Predicate<T> guard, T object, Function<T, Data<U>> positive, Data<U> negative) {
        return prependMessage((isNotNull(guard) && guard.test(object) ? positive.apply(object) : negative), nameof, "");
    }

    static <T, U> DriverFunction<T> ifDriver(
        String nameof,
        boolean status,
        Predicate<Data<U>> guard,
        DriverFunction<U> function,
        Function<Data<U>, Data<T>> positive,
        Data<T> negative
    ) {
        return DriverFunctionFactory.getFunction(ifDependency(
            nameof,
            status && areNotNull(guard, function, positive),
            DataExecutionFunctions.conditionalChain(guard, function, positive, negative),
            negative)
        );
    }

    static <T, V> DriverFunction<T> ifDriver(
        String nameof,
        boolean status,
        Function<Data<V>, String> guard,
        DriverFunction<V> function,
        Function<Data<V>, Data<T>> positive,
        Data<T> negative
    ) {
        return DriverFunctionFactory.getFunction(ifDependency(
            nameof,
            status && areNotNull(guard, function, positive),
            DataExecutionFunctions.conditionalChain(guard, function.get(), positive, negative),
            negative)
        );
    }

    static <T, U> DriverFunction<T> ifDriver(String nameof, String errorMessage, Predicate<Data<U>> guard, DriverFunction<U> function, Function<Data<U>, Data<T>> positive, Data<T> negative) {
        return DriverFunctionFactory.getFunction(ifDependency(nameof, isBlank(errorMessage) && areNotNull(guard, function, positive), DataExecutionFunctions.conditionalChain(guard, function, positive, negative), replaceMessage(negative, errorMessage)));
    }

    static <T> DriverFunction<T> ifDriver(String nameof, String errorMessage, DriverFunction<Boolean> function, DriverFunction<T> positive, Data<T> negative) {
        return DriverFunctionFactory.getFunction(ifDependency(nameof, isBlank(errorMessage) && areNotNull(function, positive, negative), SeleniumExecutor.execute(function, positive), negative));
    }

    static DriverFunction<Boolean> ifDriver(String message, DriverFunction<Boolean> positive) {
        return isBlank(message) ? positive : DriverFunctionConstants.NULL_BOOLEAN;
    }

    static DriverFunction<Integer> ifDriver(
        String nameof,
        boolean status,
        Function<Data<DecoratedList<?>>, String> guard,
        Function<WebDriver, Data<DecoratedList<?>>> function,
        Function<Data<DecoratedList<?>>, Data<Integer>> positive,
        Data<Integer> negative
    ) {
        return DriverFunctionFactory.getFunction(ifDependency(
            nameof,
            status && areNotNull(guard, function, positive),
            DataExecutionFunctions.conditionalChain(guard, function, positive, negative),
            negative)
        );
    }
}
