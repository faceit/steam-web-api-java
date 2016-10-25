package com.lukaspradel.steamapi.storefrontapi.request;

import java.util.List;

import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;
import com.lukaspradel.steamapi.storefrontapi.request.builders.AbstractSteamStorefrontApiRequestBuilder;

/**
 * Steam Web API Request appuserdetails
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#appuserdetails"
 *      >https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#appuserdetails</a>
 * @author lpradel
 *
 */
public class AppUserdetailsRequest extends SteamStorefrontApiRequest {

	private AppUserdetailsRequest(SteamStorefrontApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamStorefrontApiRequest}
	 * for request type {@link AppUserdetailsRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class AppUserdetailsRequestBuilder extends
			AbstractSteamStorefrontApiRequestBuilder {

		private final List<Integer> appIds;

		public static final String REQUEST_PARAM_APP_IDS = "appids";

		public AppUserdetailsRequestBuilder(List<Integer> appIds) {

			if (appIds.isEmpty()) {
				throw new IllegalArgumentException(
						"You must supply at least 1 App ID!");
			}

			this.appIds = appIds;
		}

		@Override
		protected SteamStorefrontApiMethod getMethod() {

			return SteamStorefrontApiMethod.APP_USER_DETAILS;
		};

		@Override
		public AppUserdetailsRequest buildRequest() {

			addListParameter(REQUEST_PARAM_APP_IDS, appIds);

			return new AppUserdetailsRequest(this);
		}
	}
}
