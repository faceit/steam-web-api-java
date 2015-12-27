package com.lukaspradel.steamapi.storefrontapi.client;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.storefrontapi.request.SteamStorefrontApiRequest;
import com.lukaspradel.steamapi.storefrontapi.request.SteamStorefrontApiRequestHandler;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;

/**
 * Create instances of this using {@link SteamStorefrontApiClientBuilder}. This
 * class should be used to pass instances of {@link SteamStorefrontApiRequest}
 * to the Steam servers and get the response-POJO.
 *
 * @author lpradel
 *
 */
public class SteamStorefrontApiClient {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private final boolean useHttps;

	private final SteamStorefrontApiRequestHandler requestHandler;

	private SteamStorefrontApiClient(SteamStorefrontApiClientBuilder builder) {

		this.useHttps = builder.useHttps;
		this.requestHandler = new SteamStorefrontApiRequestHandler(useHttps);
	}

	@SuppressWarnings({ "unchecked" })
	public <T> T processRequest(SteamStorefrontApiRequest request)
			throws SteamApiException {

		T result = null;
		String response = requestHandler.getStorefrontApiResponse(request);

		try {
			result = (T) MAPPER.readValue(response, request.getResponseType());
		} catch (IOException e) {
			throw new SteamApiException(SteamApiException.Cause.MAPPING, e);
		}
		return result;
	}

	public boolean isUseHttps() {
		return useHttps;
	}

	/**
	 * Builder class to create instances of {@link SteamWebApiClient}. Required
	 * parameters are
	 * <ul>
	 * <li>key : your Steam Web API Key</li>
	 * </ul>
	 * Optional parameters are
	 * <ul>
	 * <li>useHttps : whether to communicate with Steam via HTTPS or plain HTTP.
	 * Default is <strong>false</strong>.</li>
	 * </ul>
	 *
	 * @author lpradel
	 *
	 */
	public static class SteamStorefrontApiClientBuilder {

		private boolean useHttps = true;

		/**
		 * Creates an instance of this class. Usage of HTTPS will be
		 * <strong>enabled</strong> by default, i.e. HTTPS will be used to
		 * communicate with Steam.
		 */
		public SteamStorefrontApiClientBuilder() {
			this.useHttps = true;
		}

		public SteamStorefrontApiClientBuilder useHttps(boolean useHttps) {

			this.useHttps = useHttps;
			return this;
		}

		public SteamStorefrontApiClient build() {

			return new SteamStorefrontApiClient(this);
		}
	}
}
