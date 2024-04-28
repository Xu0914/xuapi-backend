package com.xsj.xuapiinterface;

import com.xu.xuapiclientsdk.client.XuApiClient;
import com.xu.xuapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XuapiInterfaceApplicationTests {

	@Resource
	private XuApiClient xuApiClient;

	@Test
	void contextLoads() {
		//String result = xuApiClient.getName("yupi");
		User user = new User();
		user.setUsername("liyupi");
		String userNameByPost = xuApiClient.getUserNameByPost(user);
		//System.out.println(result);
		System.out.println(userNameByPost);

	}

}
