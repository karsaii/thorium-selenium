package com.neathorium.framework.selenium.namespaces.extensions.boilers;

import java.util.function.Function;

@FunctionalInterface
public interface ScriptHandlerFunction extends ScriptFunction<Function<String, Object>> { }
