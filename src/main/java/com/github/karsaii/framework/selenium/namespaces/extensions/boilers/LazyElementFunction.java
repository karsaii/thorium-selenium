package com.github.karsaii.framework.selenium.namespaces.extensions.boilers;

import com.github.karsaii.framework.selenium.records.lazy.LazyElement;

import java.util.function.Function;

@FunctionalInterface
public interface LazyElementFunction<T> extends Function<LazyElement, DriverFunction<T>> {}
