package com.atguigu.cloud.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.micrometer.core.instrument.util.TimeUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Tag(name="支付微服务模块",description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法,json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int add = payService.add(pay);
        return ResultData.success("成功插入: "+add);
    }

    @DeleteMapping("/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") int id){
        int delete = payService.delete(id);
        return ResultData.success(delete);
    }

    @PutMapping("/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay=new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        int update = payService.update(pay);
        return ResultData.success("成功更改:"+update);
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        try{
            TimeUnit.SECONDS.sleep(62);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Pay byId = payService.getById(id);
        return ResultData.success(byId);
    }

    @GetMapping("/pay/getAll")
    public List<Pay> getAll(){
        return payService.getAll();
    }


    @Value("${server.port}")
    private String port;
    @GetMapping("/pay/get/info")
    public String getInfoByConsul(@Value("${atguigu.info}") String atguiguInfo){
        return "atguigu.INFO:"+atguiguInfo+"\t"+"port:"+port;
    }
}
