package com.lukaspradel.steamapi.storefrontapi.request;

import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;
import com.lukaspradel.steamapi.storefrontapi.request.builders.AbstractSteamStorefrontApiRequestBuilder;

/**
 * Steam Web API Request featured
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#featured"
 *      >https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#featured</a>
 * @author lpradel
 *
 */
public class FeaturedRequest extends SteamStorefrontApiRequest {

	private FeaturedRequest(SteamStorefrontApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamStorefrontApiRequest}
	 * for request type {@link FeaturedRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class FeaturedRequestBuilder extends
			AbstractSteamStorefrontApiRequestBuilder {

		@Override
		protected SteamStorefrontApiMethod getMethod() {

			return SteamStorefrontApiMethod.FEATURED;
		};

		@Override
		public FeaturedRequest buildRequest() {

			return new FeaturedRequest(this);
		}
	}
}
