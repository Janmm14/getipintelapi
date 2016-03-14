package me.minotopia.getipintelapi;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Requires a valid email address, should not only be syntactically correct as you might get contacted.
 * <p>This class holds the settings of the API getipintel.net offers.</p>
 * <p>General information about the api available at <a href="http://getipintel.net/#api">http://getipintel.net/#api</a></p>
 */
@RequiredArgsConstructor
public class GetIpIntelAPI {
	private static final String URL_START = "http://check.getipintel.net/check.php?format=json";

	private final CheckType checkType;
	/**
	 * The email address has to be valid
	 */
	private final String contactEmail;

	private String computeAddress(String ip) {
		StringBuilder sb = new StringBuilder()
							   .append(URL_START)
							   .append("&contact=")
							   .append(contactEmail)
							   .append("&ip=").append(ip);
		if (checkType.getFlag() != null) {
			sb.append('&')
				.append(checkType.getFlag())
				.append('=')
				.append(checkType.getFlagValue());
		}
		return sb.toString();
	}

	/**
	 * Ask the getipintel.net service about the given ip.
	 * <p>This method might take up to 5 seconds +network latency depending on the {@link CheckType} selected.</p>
	 *
	 * @param ip the ip to check in its default notation with 4 numbers from 0-255 and 3 dots in between. Without a starting (back-)slash and without a port.
	 * @return the LookupResult
	 * @throws IOException if an IOException occurrs while asking the service
	 */
	public LookupResult doLookup(String ip) throws IOException {
		String address = computeAddress(ip);
		URL url = new URL(address);
		HttpURLConnection con = ((HttpURLConnection) url.openConnection());
		con.setRequestMethod("GET");
		//this starts the lookup
		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		//aggregate result
		String line;
		StringBuilder responseBuilder = new StringBuilder();

		while ((line = in.readLine()) != null) {
			responseBuilder.append(line);
		}
		in.close();

		String response = responseBuilder.toString();

		//interpret result as json
		Gson gson = new Gson();
		RawLookupResult rawLookupResult = gson.fromJson(response, RawLookupResult.class);
		rawLookupResult.setResponseCode(responseCode);
		return rawLookupResult.toLookupResult();
	}
}
