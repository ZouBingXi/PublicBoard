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
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.mapper.UserTypeMapper;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.model.UserType;
import cn.szuer.publicboard.service.UserService;
import cn.szuer.publicboard.utils.mapsturctconverter.UserConverter;


@RunWith(SpringRunner.class) 
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublicboardApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private  TestRestTemplate restTemplate;
	private HttpEntity httpEntity;

	@Autowired
    private UserInfoMapper userInfoMapper;

	@Autowired
	private UserTypeMapper userTypeMapper;

	@Autowired
	private UserConverter userConverter;

	// @Autowired
	// private Mapper dozerMapper;

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

	// @Test
	// public void testDozer()
	// {
		
	// 	UserInfo userInfo = userInfoMapper.selectByPrimaryKey(2019010101);

	// 	System.out.println(userTypeMapper.selectByPrimaryKey(userInfo.getUsertype()).toString());

	// 	UserDto userDto = dozerMapper.map(userInfo, UserDto.class);

	// 	System.out.println(userDto.toString());

	// }

	@Test
	void testMapstruct()
	{
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(2019010101);
		// UserType userType = userTypeMapper.selectByPrimaryKey(userInfo.getUsertype());
		UserDto userDto = userConverter.UserInfo2UserDto(userInfo);
		System.out.println(userDto.toString());
		// System.out.println(userInfo.toString());
		// System.out.println(userType.toString());
	}




}
