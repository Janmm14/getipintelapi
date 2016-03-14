package me.minotopia.getipintelapi;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

@EqualsAndHashCode
@RequiredArgsConstructor
@SuppressWarnings("unused")
/**
 * Represents a result of the lookup without magic values
 */
public class LookupResult {

	@Getter
	private final RawLookupResult rawLookupResult;

	/**
	 * @return Whether the query was success full and a probability is included in the request.
	 */
	public boolean isSuccess() {
		return rawLookupResult.getStatus().equalsIgnoreCase("success");
	}

	/**
	 * The probability of the requested IP to be a proxy.
	 * <p>&gt;0.9: can be proxies, should be looked at</p>
	 * <p>&gt;0.98: very likely a proxy</p>
	 * <p>&lt;0.7: Low risk</p>
	 * <p>0: Whitelisted IP or no dynamic checks active and not on static ban list</p>
	 * <p>1: On static ban list, probably ip is on a public proxy list</p>
	 * <p>For more information, see <a href="http://getipintel.net/#API">http://getipintel.net/#API</a>, section "Interpretation of the Results"</p>
	 *
	 * @return the probability (between 0 and 1 inclusive)
	 * @throws IllegalStateException if the lookup was not successful
	 */
	public double getProbability() {
		if (!isSuccess()) {
			throw new IllegalStateException("no success, no probability available");
		}
		return rawLookupResult.getResult();
	}

	/**
	 * @return The ip those probability was queried
	 */
	public String getQueryIp() {
		return rawLookupResult.getQueryIP();
	}

	@Nullable
	@Deprecated
	/**
	 * @return the flags of the query
	 * @deprecated Magic value. Does not use an enum, might change in the future.
	 */
	private String getQueryFlags() { //TODO use enum, then make public
		return rawLookupResult.getQueryFlags();
	}

	/**
	 * @return the query format, which is always {@code json}
	 */
	public String getQueryFormat() {
		return rawLookupResult.getQueryFormat();
	}

	/**
	 * @return the email address we sent
	 */
	public String getContactEmail() {
		return rawLookupResult.getContact();
	}

	/**
	 * @return a human readable error message
	 * @throws IllegalStateException if the lookup was successful
	 */
	public String getErrorMessage() {
		if (isSuccess()) {
			throw new IllegalStateException("its a success, no error message available");
		}
		return rawLookupResult.getMessage();
	}

	/**
	 * @return the type of the error occurred
	 * @throws IllegalStateException if the lookup was successful
	 */
	public ErrorType getErrorType() {
		if (isSuccess()) {
			throw new IllegalStateException("its a success, no error type available");
		}
		return ErrorType.byId((int) rawLookupResult.getResult());
	}

	@Override
	public String toString() {
		if (isSuccess()) {
			return "m.m.getintelipapi.LookupResult{success=true, probability=" + getProbability() + ", queryIp=" + getQueryIp() + ", queryFormat=" + getQueryFormat() + ", contactEmail=" + getContactEmail() + '}';
		} else {
			return "m.m.getintelipapi.LookupResult{success=false, queryIp=" + getQueryIp() + ", queryFormat=" + getQueryFormat() + "contactEmail=" + getContactEmail() + ", errorMessage=" + getErrorMessage() + ", errorType=" + getErrorType() + '}';
		}
	}
}
