package me.minotopia.getipintelapi;

import lombok.Getter;

public class LookupException extends Exception {
	@Getter
    private final RawLookupResult result;
    private final Boolean rateLimited;

    public LookupException(RawLookupResult result, String message) {
        super(message + " (" + result + ')');
        this.result = result;
        this.rateLimited = null;
    }

    public LookupException(RawLookupResult result, String message, boolean rateLimited) {
        super(message + " (" + result + ')');
        this.result = result;
        this.rateLimited = rateLimited;
    }

    public boolean isRateLimited() {
        return rateLimited || result.getResult() == -5;
    }
}
