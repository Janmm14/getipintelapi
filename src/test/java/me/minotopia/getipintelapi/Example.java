package me.minotopia.getipintelapi;

import org.junit.Test;

public class Example {

	@Test
	public void exampleCheck() throws Exception {
		//create new GetIpIntelAPI object, can be used as long as you want
		GetIpIntelAPI api = new GetIpIntelAPI(CheckType.WITH_DYNAMIC_CHECKS, "iShouldBeAValidMail@web.de");

		//check a specific ip
		LookupResult result = api.doLookup("164.132.80.78"); //this ip should actually return 1 (its a datacenter ip used as proxy)

		if (!result.isSuccess()) {
			//no success :(
			//print error information
			System.out.println("Error during lookup for 164.132.80.78: " + result.getErrorMessage()
								   + " (http response code: " + result.getRawLookupResult().getResponseCode() + ','
								   + " (error type: " + result.getErrorType() + ')');
		}
		System.out.println("Probability that 164.132.80.78 is a proxy: " + result.getProbability());
	}
}
