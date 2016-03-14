package me.minotopia.getipintelapi;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the error types the GetIpIntelAPI could return.
 */
public enum ErrorType {
	/**
	 * no ip address provided
	 */
	NO_INPUT(-1),
	/**
	 * the given ip address is invalid
	 */
	INVALID_IP(-2),
	/**
	 * the given ip address is an unroutable or private ip address
	 */
	UNROUTABLE_PRIVATE_ADDRESS(-3),
	/**
	 * the databse could not be reached, likely the databse is being updated;
	 * one can check getipintel.net's twitter account for more information
	 */
	DATABASE_ERROR(-4),
	/**
	 * the ip the request was sent from was banned by the server, most likely due to eceeding the query limit;
	 * more information can be recieved through contacting getipintel.net
	 */
	IP_BANNED_RATE_LIMIT(-5),
	/**
	 * the provided contact information is invalid
	 */
	INVALID_CONTACT_INFORMATION(-6);

	private final int id;

	ErrorType(int id) {
		this.id = id;
	}

	private static Map<Integer, ErrorType> idToError = new HashMap<>(9);

	static {
		for (ErrorType errorType : values()) {
			idToError.put(errorType.id, errorType);
		}
	}

	/**
	 * @param id the id used by getipintel.net for
	 * @return
	 */
	public static ErrorType byId(int id) {
		return idToError.get(id);
	}
}
