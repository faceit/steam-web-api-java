package com.lukaspradel.steamapi.storefrontapi.request;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.storefrontapi.core.SteamStorefrontApiMethod;

public class SteamStorefrontApiRequestHandlerTest extends BaseTest {

	private SteamStorefrontApiRequestHandler requestHandlerHttps = new SteamStorefrontApiRequestHandler(
			true);

	private SteamStorefrontApiRequestHandler requestHandlerHttpsSpy = spy(requestHandlerHttps);

	@Mock
	private SteamStorefrontApiRequest requestMock;

	@Mock
	private HttpClient httpClientMock;

	@Mock
	private HttpResponse httpResponseMock;

	@Mock
	private StatusLine statusLineMock;

	@Mock
	private HttpEntity httpEntityMock;

	@Test
	public void testGetRequestUrl() throws SteamApiException {

		URI uriMock = PowerMockito.mock(URI.class);
		Map<String, String> parameters = new HashMap<String, String>();
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		when(requestMock.getBaseUrl()).thenReturn("store.steampowered.com/api");
		when(requestMock.getMethod()).thenReturn(
				SteamStorefrontApiMethod.APP_DETAILS);
		when(requestMock.getParameters()).thenReturn(parameters);
		when(requestHandlerHttpsSpy.getRequestParameters(parameters))
				.thenReturn(params);
		when(
				requestHandlerHttpsSpy.getRequestUri(any(String.class),
						any(String.class), any(String.class),
						Matchers.anyListOf(NameValuePair.class))).thenReturn(
				uriMock);

		URI actual = requestHandlerHttpsSpy.getRequestUrl(requestMock);
		verify(requestHandlerHttpsSpy).getRequestUri("https",
				"store.steampowered.com/api", "/appdetails", params);
		assertEquals(actual, uriMock);
	}

	@Test
	public void testGetRequestPath() {

		when(requestMock.getBaseUrl()).thenReturn("store.steampowered.com/api");
		when(requestMock.getMethod()).thenReturn(
				SteamStorefrontApiMethod.APP_DETAILS);

		String actual = requestHandlerHttps.getRequestPath(requestMock);
		assertEquals(actual, "/appdetails");
	}

	@Test
	public void testGetRequestParameters() {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("test-parameter", "test-value");

		List<NameValuePair> actual = requestHandlerHttps
				.getRequestParameters(parameters);
		assertEquals(actual.size(), 1);
		assertTrue(actual.contains(new BasicNameValuePair("test-parameter",
				"test-value")));
	}

	@Test
	public void testGetRequestUri() throws SteamApiException {

		String scheme = "https";
		String host = "store.steampowered.com/api";
		String path = "/appdetails";
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("appids", "58610"));

		URI actual = requestHandlerHttpsSpy.getRequestUri(scheme, host, path,
				parameters);
		assertEquals(actual.getScheme(), "https");
		assertEquals(actual.getHost(), "store.steampowered.com");
		assertEquals(actual.getPath(), "/api/appdetails");
		assertEquals(actual.getQuery(), "appids=58610");
		assertEquals(actual.toString(),
				"https://store.steampowered.com/api/appdetails?appids=58610");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetStorefrontApiResponseUnauthorized()
			throws ClientProtocolException, IOException, SteamApiException {

		when(statusLineMock.getStatusCode()).thenReturn(
				HttpStatus.SC_UNAUTHORIZED);
		when(httpResponseMock.getStatusLine()).thenReturn(statusLineMock);
		when(httpClientMock.execute(any(HttpUriRequest.class))).thenReturn(
				httpResponseMock);
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		URI uriMock = PowerMockito.mock(URI.class);

		requestHandlerHttpsSpy.getStorefrontApiResponse(uriMock);

		fail("An exception should be thrown in getWebApiResponse!");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetStorefrontApiResponseErrorCode()
			throws ClientProtocolException, IOException, SteamApiException,
			URISyntaxException {

		when(statusLineMock.getStatusCode()).thenReturn(
				HttpStatus.SC_INTERNAL_SERVER_ERROR);
		when(httpResponseMock.getStatusLine()).thenReturn(statusLineMock);
		when(httpClientMock.execute(any(HttpUriRequest.class))).thenReturn(
				httpResponseMock);
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		URI uriMock = PowerMockito.mock(URI.class);

		requestHandlerHttpsSpy.getStorefrontApiResponse(uriMock);

		fail("An exception should be thrown in getWebApiResponse!");
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testGetStorefrontApiResponseIOException()
			throws ClientProtocolException, IOException, SteamApiException {

		when(httpClientMock.execute(any(HttpUriRequest.class))).thenThrow(
				new IOException("intended-io-exception"));
		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		URI uriMock = PowerMockito.mock(URI.class);

		requestHandlerHttpsSpy.getStorefrontApiResponse(uriMock);

		fail("An exception should be thrown in getWebApiResponse!");
	}

	@Test
	public void testGetStorefrontApiResponse() throws ClientProtocolException,
			IOException, SteamApiException {

		when(requestMock.getBaseUrl()).thenReturn("store.steampowered.com");
		when(requestMock.getMethod()).thenReturn(
				SteamStorefrontApiMethod.APP_DETAILS);

		when(statusLineMock.getStatusCode()).thenReturn(HttpStatus.SC_OK);
		when(httpResponseMock.getStatusLine()).thenReturn(statusLineMock);
		when(httpResponseMock.getEntity()).thenReturn(httpEntityMock);
		when(httpClientMock.execute(any(HttpUriRequest.class))).thenReturn(
				httpResponseMock);

		when(requestHandlerHttpsSpy.getHttpClient()).thenReturn(httpClientMock);

		requestHandlerHttpsSpy.getStorefrontApiResponse(requestMock);
		verify(requestHandlerHttpsSpy)
				.getHttpResponseAsString(httpResponseMock);
	}
}
