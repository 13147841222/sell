package com.dream.sell;

import lombok.Data;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Data
public class LoggerTest {

//    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void Test1() {
//        logger.debug("debug....");
//        logger.info("info....");jdbc:mysql://192.168.30.113/sell?characterEncoding=utf-8&useSSL=false
//        logger.error("error....");
        String name = "mayc";
        String pass = "zhuml";
        log.debug("debug....");
        log.info("info....");
        log.info("name:{},pass:{}",name,pass);
        log.error("error....");
    }


}
