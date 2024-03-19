package top.ryantang.shifter.autoconfigure.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Service;
import top.ryantang.shifter.autoconfigure.properties.ZookeeperProperties;
import top.ryantang.shifter.autoconfigure.service.ZkUtilsService;

import javax.annotation.Resource;

/**
 * ZkUtilsServiceImpl.class
 *
 * @author RyanTang
 * @date 2024/3/19 15:49
 * Create by Intellij idea!
 */
@Service
@Slf4j
public class ZkUtilsServiceImpl implements ZkUtilsService {

    @Resource
    private ZookeeperProperties zookeeperProperties;


    @Override
    public void connect() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);


    }

    @Override
    public void close() {

    }
}
