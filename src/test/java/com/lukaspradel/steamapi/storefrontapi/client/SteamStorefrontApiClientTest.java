package com.lukaspradel.steamapi.storefrontapi.client;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;

import org.mockito.Mock;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lukaspradel.steamapi.BaseTest;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.featured.Featured;
import com.lukaspradel.steamapi.storefrontapi.request.FeaturedRequest;
import com.lukaspradel.steamapi.storefrontapi.request.SteamStorefrontApiRequest;
import com.lukaspradel.steamapi.storefrontapi.request.SteamStorefrontApiRequestHandler;
import com.lukaspradel.steamapi.storefrontapi.request.builders.SteamStorefrontApiRequestFactory;

public class SteamStorefrontApiClientTest extends BaseTest {

	private SteamStorefrontApiClient client = new SteamStorefrontApiClient.SteamStorefrontApiClientBuilder()
			.build();

	@Mock
	private SteamStorefrontApiRequest requestMock;

	@Mock
	private SteamStorefrontApiRequestHandler requestHandlerMock = new SteamStorefrontApiRequestHandler(
			true);

	@BeforeMethod
	public void init() {

		Whitebox.setInternalState(client, "requestHandler", requestHandlerMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionMapping() throws SteamApiException {

		when(requestHandlerMock.getStorefrontApiResponse(requestMock))
				.thenThrow(
						new SteamApiException(SteamApiException.Cause.MAPPING,
								new Throwable()));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionUnexpectedError() throws SteamApiException {

		when(requestHandlerMock.getStorefrontApiResponse(requestMock))
				.thenThrow(
						new SteamApiException(
								SteamApiException.Cause.INTERNAL_ERROR,
								new Throwable()));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionHttpError() throws SteamApiException {

		when(requestHandlerMock.getStorefrontApiResponse(requestMock))
				.thenThrow(
						new SteamApiException(
								SteamApiException.Cause.HTTP_ERROR, Integer
										.valueOf(404), "message"));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionForbiddenError() throws SteamApiException {

		when(requestHandlerMock.getStorefrontApiResponse(requestMock))
				.thenThrow(
						new SteamApiException(
								SteamApiException.Cause.FORBIDDEN, Integer
										.valueOf(403), "message"));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionInternalError() throws SteamApiException {

		when(requestHandlerMock.getStorefrontApiResponse(requestMock))
				.thenThrow(
						new SteamApiException(
								SteamApiException.Cause.INTERNAL_ERROR, Integer
										.valueOf(500), "message"));

		client.processRequest(requestMock);
	}

	@Test(expectedExceptions = SteamApiException.class)
	public void testProcessExceptionUnexpectedStatusError()
			throws SteamApiException {

		when(requestHandlerMock.getStorefrontApiResponse(requestMock))
				.thenThrow(
						new SteamApiException(SteamApiException.Cause.MAPPING,
								Integer.valueOf(0), "message"));

		client.processRequest(requestMock);
	}

	@Test
	public void testProcessExceptionMessage() throws SteamApiException {

		when(requestHandlerMock.getStorefrontApiResponse(requestMock))
				.thenThrow(
						new SteamApiException(
								SteamApiException.Cause.HTTP_ERROR, Integer
										.valueOf(404), "message"));

		try {
			client.processRequest(requestMock);

			fail("An exception of type SteamApiException should have been thrown here!");
		} catch (SteamApiException e) {
			assertEquals(
					e.getMessage(),
					"The Web API request failed with the following HTTP error: message (status code: 404).");
		}
	}

	@Test
	public void testProcessFeaturedRequest() throws SteamApiException,
			IOException {

		FeaturedRequest featuredRequest = SteamStorefrontApiRequestFactory
				.createFeaturedRequest();

		String mockAnswer = readResourceAsString("Featured.json");

		when(requestHandlerMock.getStorefrontApiResponse(featuredRequest))
				.thenReturn(mockAnswer);

		Featured featured = client.<Featured> processRequest(featuredRequest);

		assertNotNull(featured);
		assertTrue(featured.getAdditionalProperties().isEmpty());
		assertTrue(featured.getLargeCapsules().isEmpty());
		assertFalse(featured.getFeaturedLinux().isEmpty());
		assertFalse(featured.getFeaturedMac().isEmpty());
		assertFalse(featured.getFeaturedWin().isEmpty());
		assertEquals("defcon3", featured.getLayout());
		assertEquals(Integer.valueOf(1), featured.getStatus());
	}

	// @Test
	// public void bla() throws SteamApiException {
	//
	// client = new SteamStorefrontApiClient.SteamStorefrontApiClientBuilder()
	// .build();
	// Appdetails featured = client
	// .processRequest(new FeaturedRequest.FeaturedRequestBuilder()
	// .buildRequest());
	// System.out.println(featured.getStatus());
	// }
}
