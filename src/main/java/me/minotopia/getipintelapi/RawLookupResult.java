package me.minotopia.getipintelapi;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString
@EqualsAndHashCode
public class RawLookupResult {
    private String status; //"success" or "error"
    private double result;
    private String queryIP;
	@Nullable
    private String queryFlags;
    private String queryFormat;
    private String contact;

    private String message;

	public LookupResult toLookupResult() {
		return new LookupResult(this);
	}
}
