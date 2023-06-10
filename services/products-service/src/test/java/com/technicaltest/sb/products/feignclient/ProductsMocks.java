package com.technicaltest.sb.products.feignclient;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

import java.io.IOException;

public class ProductsMocks {
	public static void setUpMockProductsResponse(WireMockServer mockServer) throws IOException {
		mockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/products"))
				.willReturn(WireMock.aResponse().withStatus(HttpStatus.OK.value())
						.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withBody(copyToString(
								ProductsMocks.class.getClassLoader()
								.getResourceAsStream("get-products-response.json"),
								defaultCharset()))));
	}
}
