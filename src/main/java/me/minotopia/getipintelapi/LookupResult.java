package me.minotopia.getipintelapi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public class LookupResult {

	@Getter
	private final RawLookupResult rawLookupResult;

	public boolean isSuccess() {
		return rawLookupResult.getStatus().equalsIgnoreCase("success");
	}

	/**
	 * throws
	 * @return
     */
	public double getProbability() {
		if (!isSuccess()) {
			throw new IllegalStateException("no success, no probability available");
		}
		return rawLookupResult.getResult();
	}

	public String getQueryIp() {
		return rawLookupResult.getQueryIP();
	}

	@Nullable
	@Deprecated
	public String getQueryFlags() { //TODO use enum
		return rawLookupResult.getQueryFlags();
	}

	/**
	 * @return the query format, which is always {@code json}
     */
	public String getQueryFormat() {
		return rawLookupResult.getQueryFormat();
	}

	/**
	 * this is already given to the api, it just returns the same information
	 * @return the sent email the lookup is done with
     */
	public String getContactEmail() {
		return rawLookupResult.getContact();
	}

	public String getErrorMessage() {
		if (isSuccess()) {
			throw new IllegalStateException("its a success, no error message available");
		}
		return rawLookupResult.getMessage();
	}

	public IpIntelErrorType getError() {
		if (isSuccess()) {
			throw new IllegalStateException("its a success, no error type available");
		}
		return IpIntelErrorType.byId((int) rawLookupResult.getResult());
	}
}
