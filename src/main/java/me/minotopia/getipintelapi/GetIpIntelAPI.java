package me.minotopia.getipintelapi;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Requires a valid email address, should not only be syntactically correct
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
        return URL_START + "&contact=" + contactEmail + '&' + checkType.getFlag() + '=' + checkType.getFlagValue() + "&ip=" + ip;
    }

    public LookupResult doLookup(String ip) throws IOException {
        String address = computeAddress(ip);
        URL url = new URL(address);
        HttpURLConnection con = ((HttpURLConnection) url.openConnection());
        con.setRequestMethod("GET");
        //con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder responseBuilder = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            responseBuilder.append(inputLine);
        }
        in.close();

        //print result
        String response = responseBuilder.toString();

        Gson gson = new Gson();
        RawLookupResult rawLookupResult = gson.fromJson(response, RawLookupResult.class);
		rawLookupResult.setResponseCode(responseCode);
		return rawLookupResult.toLookupResult();
    }
}
