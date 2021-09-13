package com.infybuzz.service;

import com.infybuzz.feignclients.AddressFeignClient;
import com.infybuzz.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentConnectivityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentConnectivityService.class);

	private final AddressFeignClient addressFeignClient;

	private Long count = 0L;

	public StudentConnectivityService(AddressFeignClient addressFeignClient) {
		this.addressFeignClient = addressFeignClient;
	}

	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
	public AddressResponse getAddressById(Long addressId) {
		count++;
		LOGGER.info("count = {}", count);
		return addressFeignClient.getById(addressId);
	}

	public AddressResponse fallbackGetAddressById(Long addressId, Throwable th) {
		LOGGER.error("Error getting AddressResponse for addressId {}", addressId, th);
		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setCity("DUMMY CITY");
		addressResponse.setStreet("DUMMY STREET");
		return addressResponse;
	}
}
