package com.github.karsaii.framework.selenium.constants.clipboard;

import com.github.karsaii.core.namespaces.systemidentity.BasicSystemIdentityFunctions;
import org.openqa.selenium.Keys;

public abstract class CopyPasteConstants {
    public static final String PLATFORM_COPYPASTE_CHORD = Keys.chord(BasicSystemIdentityFunctions.isMac() ? Keys.COMMAND : Keys.CONTROL, "v");
}
