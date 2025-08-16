package kz.mn.partners.mnp.v1.biletter.client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ProviderFeignClient", url = "${spring.service.payment.url}/api/v1/")
public interface ProviderFeignClient {
}
