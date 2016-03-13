package me.minotopia.getipintelapi;

import java.util.HashMap;
import java.util.Map;

public enum IpIntelErrorType {
	NO_INPUT(-1),
	INVALID_IP(-2),
	UNROUTABLE_PRIVATE_ADDRESS(-3),
	DATABASE_ERROR(-4),
	IP_BANNED_RATE_LIMIT(-5),
	INVALID_CONTACT_INFORMATION(-6);

	private final int id;

	IpIntelErrorType(int id) {
		this.id = id;
	}

	private static Map<Integer, IpIntelErrorType> idToError = new HashMap<>(9);

	static {
		for (IpIntelErrorType ipIntelErrorType : values()) {
			idToError.put(ipIntelErrorType.id, ipIntelErrorType);
		}
	}

	public static IpIntelErrorType byId(int id) {
		return idToError.get(id);
	}
}
