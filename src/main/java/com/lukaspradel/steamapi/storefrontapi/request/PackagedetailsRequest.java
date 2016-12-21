package com.lukaspradel.steamapi.storefrontapi.request;

import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;
import com.lukaspradel.steamapi.storefrontapi.request.builders.AbstractSteamStorefrontApiRequestBuilder;

/**
 * Steam Web API Request packagedetails
 *
 * @see <a href=
 *      "https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#packagedetails"
 *      >https://wiki.teamfortress.com/wiki/User:RJackson/StorefrontAPI#packagedetails</a>
 * @author lpradel
 *
 */
public class PackagedetailsRequest extends SteamStorefrontApiRequest {

	private PackagedetailsRequest(SteamStorefrontApiRequestBuilder builder) {
		super(builder);
	}

	/**
	 * Builder object to create instances of {@link SteamStorefrontApiRequest}
	 * for request type {@link PackagedetailsRequest}.
	 *
	 * @author lpradel
	 *
	 */
	public static class PackagedetailsRequestBuilder extends
			AbstractSteamStorefrontApiRequestBuilder {

		private final Integer packageIds;

		public static final String REQUEST_PARAM_PACKAGE_IDS = "packageids";

		public PackagedetailsRequestBuilder(Integer packageIds) {

			this.packageIds = packageIds;
		}

		@Override
		protected SteamStorefrontApiMethod getMethod() {

			return SteamStorefrontApiMethod.PACKAGE_DETAILS;
		};

		@Override
		public PackagedetailsRequest buildRequest() {

			addParameter(REQUEST_PARAM_PACKAGE_IDS, packageIds);

			return new PackagedetailsRequest(this);
		}
	}
}
