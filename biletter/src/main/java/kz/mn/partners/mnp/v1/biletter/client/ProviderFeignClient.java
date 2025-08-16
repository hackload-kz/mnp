package kz.mn.partners.mnp.v1.biletter.client;


import org.springframework.cloud.openfeign.FeignClient;

import static kz.mn.partners.mnp.v1.biletter.common.constant.Constants.API_V1_PATH;

@FeignClient(name = "ProviderFeignClient", url = "${spring.service.payment.url}" + API_V1_PATH)
public interface ProviderFeignClient {



}
