package com.lukaspradel.steamapi.storefrontapi.request.builders;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;
import com.lukaspradel.steamapi.storefrontapi.request.AppUserdetailsRequest;
import com.lukaspradel.steamapi.storefrontapi.request.AppdetailsRequest;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedCategoriesRequest;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedRequest;
import com.lukaspradel.steamapi.storefrontapi.request.PackagedetailsRequest;

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

	@Test
	public void testCreateAppUserdetailsRequest() {

		List<Integer> appIds = new ArrayList<Integer>();
		appIds.add(49520);
		appIds.add(570);

		AppUserdetailsRequest request = SteamStorefrontApiRequestFactory
				.createAppUserdetailsRequest(appIds);

		assertNotNull(request);
		assertEquals(SteamStorefrontApiMethod.APP_USER_DETAILS,
				request.getMethod());
		assertFalse(request.getParameters().isEmpty());
		assertEquals(1, request.getParameters().size());
		assertEquals("49520,570", request.getParameters().get("appids"));
	}

	@Test
	public void testCreatePackagedetailsRequest() {

		PackagedetailsRequest request = SteamStorefrontApiRequestFactory
				.createPackagedetailsRequest(123);

		assertNotNull(request);
		assertEquals(SteamStorefrontApiMethod.PACKAGE_DETAILS,
				request.getMethod());
		assertFalse(request.getParameters().isEmpty());
		assertEquals(1, request.getParameters().size());
		assertEquals("123", request.getParameters().get("packageids"));
	}
}
