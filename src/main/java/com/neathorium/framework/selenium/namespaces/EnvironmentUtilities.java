package com.neathorium.framework.selenium.namespaces;

import com.neathorium.framework.selenium.constants.EnvironmentPropertyConstants;
import com.neathorium.core.constants.validators.CoreFormatterConstants;

import java.nio.file.Paths;

public interface EnvironmentUtilities {
    static String getUsersProjectRootDirectory() {
        var rootDir = Paths.get(".").normalize().toAbsolutePath().toString();
        if (!rootDir.startsWith(EnvironmentPropertyConstants.USER_DIR)) {
            throw new RuntimeException("Root directory not found in user directory" + CoreFormatterConstants.END_LINE);
        }

        return rootDir;
    }
}
