package com.lukaspradel.steamapi.storefrontapi.request.builders;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;
import com.lukaspradel.steamapi.storefrontapi.request.AppdetailsRequest;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedCategoriesRequest;
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

	@Test
	public void testCreateFeaturedCategoriesRequest() {

		FeaturedCategoriesRequest request = SteamStorefrontApiRequestFactory
				.createFeaturedCategoriesRequest();

		assertNotNull(request);
		assertEquals(SteamStorefrontApiMethod.FEATURED_CATEGORIES,
				request.getMethod());
		assertTrue(request.getParameters().isEmpty());
	}

	@Test
	public void testCreateAppdetailsRequest() {

		AppdetailsRequest request = SteamStorefrontApiRequestFactory
				.createAppdetailsRequest(49520);

		assertNotNull(request);
		assertEquals(SteamStorefrontApiMethod.APP_DETAILS, request.getMethod());
		assertFalse(request.getParameters().isEmpty());
		assertEquals(1, request.getParameters().size());
		assertEquals("49520", request.getParameters().get("appids"));
	}
}
