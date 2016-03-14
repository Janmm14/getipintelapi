package me.minotopia.getipintelapi;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@Getter
@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
/**
 * Represents the raw result of the lookup, to be used in conjunction with gson.
 * <p>Use {@link #toLookupResult()}</p>
 */
public class RawLookupResult {
	private String status; //"success" or "error"
	private double result;
	private String queryIP;
	@Nullable
	private String queryFlags;
	private String queryFormat;
	private String contact;

	private String message;

	@Setter(AccessLevel.PACKAGE)
	private Integer responseCode;

	public LookupResult toLookupResult() {
		return new LookupResult(this);
	}
}
