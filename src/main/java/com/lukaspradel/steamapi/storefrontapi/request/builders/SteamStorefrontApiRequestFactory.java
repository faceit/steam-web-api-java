package com.lukaspradel.steamapi.storefrontapi.request.builders;

import com.lukaspradel.steamapi.storefrontapi.request.FeaturedCategoriesRequest;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedCategoriesRequest.FeaturedCategoriesRequestBuilder;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedRequest;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedRequest.FeaturedRequestBuilder;
import com.lukaspradel.steamapi.storefrontapi.request.SteamStorefrontApiRequest;

/**
 * Convenience class to create instances of {@link SteamStorefrontApiRequest}.
 * Alternatively use the *RequestBuilder builders.
 *
 * @author lpradel
 *
 */
public abstract class SteamStorefrontApiRequestFactory {

	public static FeaturedRequest createFeaturedRequest() {

		return new FeaturedRequestBuilder().buildRequest();
	}

	public static FeaturedCategoriesRequest createFeaturedCategoriesRequest() {

		return new FeaturedCategoriesRequestBuilder().buildRequest();
	}
}
