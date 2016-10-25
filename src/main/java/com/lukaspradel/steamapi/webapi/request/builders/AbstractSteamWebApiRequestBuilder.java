package com.lukaspradel.steamapi.webapi.request.builders;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest.SteamWebApiRequestBuilder;

/**
 * Skeleton of {@link SteamWebApiRequestBuilder} that holds (common) request
 * parameters and implements shared instantiation of {@link SteamWebApiRequest}.
 *
 * @author lpradel
 *
 */
public abstract class AbstractSteamWebApiRequestBuilder extends
		SteamWebApiRequestBuilder {

	protected Map<String, String> parameters = new HashMap<String, String>();

	static final String REQUEST_PARAM_FORMAT = "format";

	static final String REQUEST_PARAM_FORMAT_JSON = "json";

	public AbstractSteamWebApiRequestBuilder() {

		parameters.put(REQUEST_PARAM_FORMAT, REQUEST_PARAM_FORMAT_JSON);
	}

	public abstract SteamWebApiRequest buildRequest();

	protected void addParameter(String name, String value) {

		parameters.put(name, value);
	}

	protected void addParameter(String name, Integer value) {

		parameters.put(name, String.valueOf(value));
	}

	/**
	 * Adds List-parameter as comma-separated strings
	 * 
	 * @param <T>
	 *            Type of the parameters (e.g. String, Integer, ...)
	 * @param name
	 *            Name of the List-parameter
	 * @param valueList
	 *            List of the comma-separated strings
	 */
	protected <T> void addListParameter(String name, List<T> valueList) {

		StringBuilder paramValue = new StringBuilder();

		for (T value : valueList) {
			paramValue.append(value.toString());
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
	 * @param <T>
	 *            Type of the parameters (e.g. String, Integer, ...)
	 * @param name
	 *            Name of the Array-parameter
	 * @param valueList
	 *            List of the comma-separated strings
	 */
	protected <T> void addArrayParameter(String name, List<T> valueList) {

		int i = 0;

		for (T value : valueList) {

			StringBuilder paramName = new StringBuilder();
			StringBuilder paramValue = new StringBuilder();

			paramName.append(name);
			paramName.append("[");
			paramName.append(i);
			paramName.append("]");

			paramValue.append(value.toString());

			addParameter(paramName.toString(), paramValue.toString());

			i++;
		}
	}

	@Override
	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}
}
