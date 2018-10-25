package com.cczu.spider;

import com.alibaba.fastjson.JSONObject;
import com.cczu.spider.pojo.CoursePojo;
import com.cczu.spider.pojo.TestEntity1;
import com.cczu.spider.pojo.TestEntity2;
import com.cczu.spider.service.RedisService;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {
	@Autowired
	private RedisService redisService;

	@Resource
	private Mapper mapper;
	private JSONObject json = new JSONObject();

	@Test
	public void testMapper() {
		TestEntity1 entity1 = new TestEntity1();
		entity1.setAddr("addr");
		entity1.setAge(26);
		entity1.setUserName("userName");
        TestEntity2 entity2 = mapper.map(entity1, TestEntity2.class);
        System.out.println(entity2.getAddr() + entity2.getUserName());
    }

	@Test
	public void contextLoads() {
	}


	@Test
	public void setString() {
//		redisService.set("13446212", "[][]");
	}
	@Test
	public void getString() {
//		String result = redisService.get("13446212q");
//		List<CoursePojo> pojos = json.parseArray(result, CoursePojo.class);
//
//		System.out.println(pojos);
	}
}
