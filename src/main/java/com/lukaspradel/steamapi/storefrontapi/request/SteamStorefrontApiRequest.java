package com.lukaspradel.steamapi.storefrontapi.request;

import java.util.Collections;
import java.util.Map;

import com.lukaspradel.steamapi.core.SteamApiRequest;
import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;

/**
 * Encapsulates a request to the Steam Storefront API with all its required and
 * optional properties.
 *
 * @author lpradel
 *
 */
public class SteamStorefrontApiRequest extends SteamApiRequest {

	static final String STOREFRONT_API_BASE_URL = "store.steampowered.com/api";

	private final SteamStorefrontApiMethod method;

	protected SteamStorefrontApiRequest(SteamStorefrontApiRequestBuilder builder) {

		super(STOREFRONT_API_BASE_URL, builder.getParameters());

		this.method = builder.getMethod();
	}

	public SteamStorefrontApiMethod getMethod() {
		return method;
	}

	public Class<?> getResponseType() {

		return method.getReponseType();
	}

	/**
	 * Generic builder class to create instances of
	 * {@link SteamStorefrontApiRequest}. Consider using subclasses of this to
	 * create concrete requests for covenience.
	 *
	 * @author lpradel
	 *
	 */
	public static class SteamStorefrontApiRequestBuilder {

		private final SteamStorefrontApiMethod method;

		private final Map<String, String> parameters;

		protected SteamStorefrontApiRequestBuilder() {

			method = null;
			parameters = null;
		}

		protected SteamStorefrontApiRequestBuilder(
				SteamStorefrontApiMethod method, Map<String, String> parameters) {

			this.method = method;
			this.parameters = parameters;
		}

		protected SteamStorefrontApiMethod getMethod() {

			return method;
		}

		protected Map<String, String> getParameters() {

			return Collections.unmodifiableMap(parameters);
		}

		public SteamStorefrontApiRequest build() {

			return new SteamStorefrontApiRequest(this);
		}
	}
}
