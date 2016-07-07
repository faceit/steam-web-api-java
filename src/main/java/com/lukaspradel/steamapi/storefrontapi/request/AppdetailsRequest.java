package com.lukaspradel.steamapi.storefrontapi.request;

import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;
import com.lukaspradel.steamapi.storefrontapi.request.builders.AbstractSteamStorefrontApiRequestBuilder;

/**
 * Steam Web API Request appdetails
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#appdetails"
 *      >https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#appdetails</a>
 * @author lpradel
 *
 */
public class AppdetailsRequest extends SteamStorefrontApiRequest {

	private AppdetailsRequest(SteamStorefrontApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamStorefrontApiRequest}
	 * for request type {@link AppdetailsRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class AppdetailsRequestBuilder extends
			AbstractSteamStorefrontApiRequestBuilder {

		private final Integer appIds;

		public static final String REQUEST_PARAM_APP_IDS = "appids";

		public AppdetailsRequestBuilder(Integer appIds) {

			this.appIds = appIds;
		}

		@Override
		protected SteamStorefrontApiMethod getMethod() {

			return SteamStorefrontApiMethod.APP_DETAILS;
		};

		@Override
		public AppdetailsRequest buildRequest() {

			addParameter(REQUEST_PARAM_APP_IDS, appIds);

			return new AppdetailsRequest(this);
		}
	}
}
