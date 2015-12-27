package com.lukaspradel.steamapi.storefrontapi.request.builders;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedRequest;

public class SteamStorefrontApiRequestFactoryTest extends BaseTest {

	@Test
	public void testCreateFeaturedRequest() {

		FeaturedRequest request = SteamStorefrontApiRequestFactory
				.createFeaturedRequest();

		assertNotNull(request);
		assertEquals(SteamStorefrontApiMethod.FEATURED, request.getMethod());
		assertTrue(request.getParameters().isEmpty());
	}
}
