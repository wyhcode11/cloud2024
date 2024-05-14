package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient("cloud-payment-service")
@FeignClient("cloud-gateway")
public interface PayFeignApi {
    @PostMapping("/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);
    @GetMapping("/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id")Integer id);
    @GetMapping("/pay/get/info")
    public String mylb();
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();
}
