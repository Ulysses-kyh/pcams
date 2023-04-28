package com.suda.pcams;

import com.suda.pcams.proxy.PcamsLoginProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import xyz.erupt.core.annotation.EruptScan;
import xyz.erupt.upms.fun.EruptLogin;

/**
 * 启动类
 *
 * @author Ulysses
 * @since 2023-04-23
 */

@EntityScan
@EruptScan
@EruptLogin(PcamsLoginProxy.class)
@SpringBootApplication
public class PcamsApplication {

    public static void main(String[] args) {

        SpringApplication.run(PcamsApplication.class, args);
    }

}
