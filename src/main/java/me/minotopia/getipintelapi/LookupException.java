package me.minotopia.getipintelapi;

import lombok.Getter;

public class LookupException extends Exception {
	@Getter
    private final LookupResult result;
    private final Boolean rateLimited;

    public LookupException(LookupResult result, String message) {
        super(message + "(" + result + ')');
        this.result = result;
        this.rateLimited = null;
    }

    public LookupException(LookupResult result, String message, boolean rateLimited) {
        super(message + "(" + result + ')');
        this.result = result;
        this.rateLimited = null;
    }

    public boolean isRateLimited() {
        return rateLimited || result.getResult() == -5;
    }
}
