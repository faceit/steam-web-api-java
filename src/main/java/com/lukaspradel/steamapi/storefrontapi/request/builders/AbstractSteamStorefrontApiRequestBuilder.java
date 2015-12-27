package com.lukaspradel.steamapi.storefrontapi.request.builders;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lukaspradel.steamapi.storefrontapi.request.SteamStorefrontApiRequest;
import com.lukaspradel.steamapi.storefrontapi.request.SteamStorefrontApiRequest.SteamStorefrontApiRequestBuilder;

/**
 * Skeleton of {@link SteamStorefrontApiRequestBuilder} that holds (common)
 * request parameters and implements shared instantiation of
 * {@link SteamStorefrontApiRequest}.
 *
 * @author lpradel
 *
 */
public abstract class AbstractSteamStorefrontApiRequestBuilder extends
		SteamStorefrontApiRequestBuilder {

	protected Map<String, String> parameters = new HashMap<String, String>();

	public AbstractSteamStorefrontApiRequestBuilder() {
	}

	public abstract SteamStorefrontApiRequest buildRequest();

	protected void addParameter(String name, String value) {

		parameters.put(name, value);
	}

	protected void addParameter(String name, Integer value) {

		parameters.put(name, String.valueOf(value));
	}

	/**
	 * Adds List-parameter as comma-separated strings
	 *
	 * @param name
	 *            Name of the List-parameter
	 * @param valueList
	 *            List of the comma-separated strings
	 */
	protected void addListParameter(String name, List<String> valueList) {

		StringBuilder paramValue = new StringBuilder();

		for (String value : valueList) {
			paramValue.append(value);
			paramValue.append(",");
		}
		paramValue.setLength(paramValue.length() - 1);

		addParameter(name, paramValue.toString());
	}

	/**
	 * Adds Array-parameter as comma-separated array-strings. For example, an
	 * array parameter by the name of "param" with the values "val1, val2" would
	 * be written as "param[0]=val1,param[1]=val2".
	 *
	 * @param name
	 *            Name of the Array-parameter
	 * @param valueList
	 *            List of the comma-separated strings
	 */
	protected void addArrayParameter(String name, List<String> valueList) {

		int i = 0;

		for (String value : valueList) {

			StringBuilder paramName = new StringBuilder();
			StringBuilder paramValue = new StringBuilder();

			paramName.append(name);
			paramName.append("[");
			paramName.append(i);
			paramName.append("]");

			paramValue.append(value);

			addParameter(paramName.toString(), paramValue.toString());

			i++;
		}
	}

	@Override
	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}
}
