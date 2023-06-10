package com.technicaltest.sb.products.feignclient;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.technicaltest.sb.products.service.interfaces.IProductService;


/**
 * 
 * @author edenilson
 * @version 1.0
 */
@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
class FeignFakeStoreServiceTest {
	

	//@Autowired
	private WireMockServer mockProductsService;
	
	//@Autowired
	private IProductService productsClient;
	
	//@BeforeEach
	void setUp() throws IOException {
		ProductsMocks.setUpMockProductsResponse(mockProductsService);
	}
	
	//@Test
	void whenGetProductsThenBooksListShouldBeReturned() {
		assertFalse(productsClient.getAll().isEmpty());
	}
	
}
