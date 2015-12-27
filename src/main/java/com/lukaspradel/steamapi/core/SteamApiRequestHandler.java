package com.lukaspradel.steamapi.core;


/**
 * Generic request handler for common properties of all APIs provided by Steam.
 *
 * @author lpradel
 *
 */
public abstract class SteamApiRequestHandler {

	private static final String PROTOCOL_HTTPS = "https";

	private static final String PROTOCOL_HTTP = "http";

	private boolean useHttps;

	public SteamApiRequestHandler(boolean useHttps) {

		this.useHttps = useHttps;
	}

	public boolean isUseHttps() {
		return useHttps;
	}

	public String getProtocol() {

		return isUseHttps() ? PROTOCOL_HTTPS : PROTOCOL_HTTP;
	}
}
