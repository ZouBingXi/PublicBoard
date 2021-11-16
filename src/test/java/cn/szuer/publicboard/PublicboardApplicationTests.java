package cn.szuer.publicboard;

import java.util.List;

import com.github.pagehelper.PageInfo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.service.UserService;


@RunWith(SpringRunner.class) 
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublicboardApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private  TestRestTemplate restTemplate;
	private HttpEntity httpEntity;

	@Test
	void contextLoads() {
	}

	@Test
	void testUserService(){

		List<UserDto> list = userService.getAll();
		System.out.println(list);
		PageInfo<UserDto> pageInfo = userService.getByPage(1, 3);
		System.out.println(pageInfo);
	} 

	@Test
	void login() throws Exception{

		String url = "/user/login";
		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("userid", "");
		map.add("password", "");
		ResponseEntity responseEntity = restTemplate.postForEntity(url, map, String.class);
		System.out.println(responseEntity.getBody());

	}

}
