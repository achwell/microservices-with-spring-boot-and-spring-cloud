package com.infybuzz.feignclients;

import com.infybuzz.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//To run without eureka
//@FeignClient(url="${address.service.url}", path = "${address.service.path}", value = "address-feign-client")

//To run with Eureka, and not gateway api
//@FeignClient(path = "${address.service.path}", value = "address-service")

//To run with gateway api
@FeignClient(value = "api-gateway")
public interface AddressFeignClient {

    //To run without gateway api
//    @GetMapping("/getById/{id}")
//To run with gateway api
    @GetMapping("address-service/${address.service.path}/getById/{id}")
    AddressResponse getById(@PathVariable Long id);
}
