package com.neathorium.framework.selenium.namespaces.extensions.boilers;

import com.neathorium.framework.selenium.records.lazy.LazyElement;

import java.util.function.Function;

@FunctionalInterface
public interface LazyElementFunction<T> extends Function<LazyElement, DriverFunction<T>> {}
