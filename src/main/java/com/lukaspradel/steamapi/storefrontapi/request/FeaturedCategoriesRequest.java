package com.lukaspradel.steamapi.storefrontapi.request;

import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;
import com.lukaspradel.steamapi.storefrontapi.request.builders.AbstractSteamStorefrontApiRequestBuilder;

/**
 * Steam Web API Request featuredcategories
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#featuredcategories"
 *      >https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#featuredcategories</a>
 * @author lpradel
 *
 */
public class FeaturedCategoriesRequest extends SteamStorefrontApiRequest {

	private FeaturedCategoriesRequest(SteamStorefrontApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamStorefrontApiRequest}
	 * for request type {@link FeaturedRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class FeaturedCategoriesRequestBuilder extends
			AbstractSteamStorefrontApiRequestBuilder {

		@Override
		protected SteamStorefrontApiMethod getMethod() {

			return SteamStorefrontApiMethod.FEATURED_CATEGORIES;
		};

		@Override
		public FeaturedCategoriesRequest buildRequest() {

			return new FeaturedCategoriesRequest(this);
		}
	}
}
