package com.neathorium.framework.selenium.namespaces.scripter;

import static com.neathorium.framework.selenium.constants.ScriptGeneralStringConstants.IF;
import static com.neathorium.framework.selenium.constants.ScriptGeneralStringConstants.IF_END;
import static com.neathorium.framework.selenium.constants.ScriptGeneralStringConstants.RETURN;
import static com.neathorium.framework.selenium.constants.ScriptGeneralStringConstants.SCOPE_END;
import static com.neathorium.framework.selenium.constants.ScriptGeneralStringConstants.UNLESS;

public interface General {
    static String RETURN(String conditionExpression) {
        return RETURN + conditionExpression + ";";
    }

    static String IF(String conditionExpression, String body) {
        return IF + conditionExpression + IF_END + body + SCOPE_END;
    }

    static String IF_FALSE_RETURN_FALSE(String conditionExpression) {
        return UNLESS + conditionExpression + IF_END + RETURN("false") + SCOPE_END;
    }

    static String IF_RETURN(String conditionExpression) {
        return IF(conditionExpression, RETURN(conditionExpression));
    }

    static String IF_RETURN(String conditionExpression, String returnExpression) {
        return IF(conditionExpression, RETURN(returnExpression));
    }

    static String IF_CONTINUE(String conditionExpression) {
        return IF + conditionExpression + IF_END + "continue;" + SCOPE_END;
    }

    static String IF_THEN(String conditionExpression, String body) {
        return IF + conditionExpression + IF_END + body + SCOPE_END;
    }

}
