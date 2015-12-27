package com.lukaspradel.steamapi.storefrontapi.request;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Collections;

import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;
import com.lukaspradel.steamapi.storefrontapi.request.SteamStorefrontApiRequest.SteamStorefrontApiRequestBuilder;

public class SteamStorefrontApiRequestTest extends BaseTest {

	@Test
	public void testBuilder() {

		SteamStorefrontApiRequestBuilder builder = new SteamStorefrontApiRequestBuilder(
				SteamStorefrontApiMethod.APP_DETAILS,
				Collections.<String, String> emptyMap());

		SteamStorefrontApiRequest request = builder.build();

		assertEquals(request.getBaseUrl(),
				SteamStorefrontApiRequest.STOREFRONT_API_BASE_URL);
		assertEquals(request.getMethod(), SteamStorefrontApiMethod.APP_DETAILS);
		assertTrue(request.getParameters().isEmpty());
	}
}
