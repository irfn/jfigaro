package com.gojek;

import java.util.Set;

public class MissingKeysException extends Throwable {
    public MissingKeysException(Set<String> missingKeys) {
        super("Missing required configuration keys: " + missingKeys);
    }
}
