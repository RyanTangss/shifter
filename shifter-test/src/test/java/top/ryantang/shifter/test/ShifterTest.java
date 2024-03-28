package top.ryantang.shifter.test;

import com.alibaba.fastjson2.JSONObject;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.ryantang.shifter.autoconfigure.properties.ShifterProperties;
import top.ryantang.shifter.autoconfigure.properties.ZookeeperProperties;

import javax.annotation.Resource;

/**
 * ShifterTest.class
 *
 * @author RyanTang
 * @date 2024/3/25 11:05
 * Create by Intellij idea!
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShifterTest {

    @Resource
    private ShifterProperties shifterProperties;

    @Resource
    private CuratorFramework curatorFramework;

    @Test
    public void shifterPropertiesTest() {
        ZookeeperProperties zookeeper = shifterProperties.getZookeeper();
        System.out.println(zookeeper);
    }

    @Test
    public void curatorFrameworkTest() throws Exception {
//        CuratorZookeeperClient zookeeperClient = curatorFramework.getZookeeperClient();
//
//        System.out.println(JSONObject.toJSONString(zookeeperClient));
        GetChildrenBuilder children = curatorFramework.getChildren();
        System.out.println(JSONObject.toJSONString(children));
    }
}
