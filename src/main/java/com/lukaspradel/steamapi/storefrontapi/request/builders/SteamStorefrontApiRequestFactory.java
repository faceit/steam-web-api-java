package com.lukaspradel.steamapi.storefrontapi.request.builders;

import java.util.List;

import com.lukaspradel.steamapi.storefrontapi.request.AppUserdetailsRequest;
import com.lukaspradel.steamapi.storefrontapi.request.AppUserdetailsRequest.AppUserdetailsRequestBuilder;
import com.lukaspradel.steamapi.storefrontapi.request.AppdetailsRequest;
import com.lukaspradel.steamapi.storefrontapi.request.AppdetailsRequest.AppdetailsRequestBuilder;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedCategoriesRequest;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedCategoriesRequest.FeaturedCategoriesRequestBuilder;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedRequest;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedRequest.FeaturedRequestBuilder;
import com.lukaspradel.steamapi.storefrontapi.request.PackagedetailsRequest;
import com.lukaspradel.steamapi.storefrontapi.request.PackagedetailsRequest.PackagedetailsRequestBuilder;
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

	public static AppdetailsRequest createAppdetailsRequest(int appIds) {

		return new AppdetailsRequestBuilder(appIds).buildRequest();
	}

	public static AppUserdetailsRequest createAppUserdetailsRequest(
			List<Integer> appIds) {

		return new AppUserdetailsRequestBuilder(appIds).buildRequest();
	}

	public static PackagedetailsRequest createPackagedetailsRequest(
			int packageIds) {

		return new PackagedetailsRequestBuilder(packageIds).buildRequest();
	}
}
