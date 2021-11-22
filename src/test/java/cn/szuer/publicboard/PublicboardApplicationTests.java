package cn.szuer.publicboard;

import java.util.List;

import com.github.pagehelper.PageInfo;

// import com.github.pagehelper.PageInfo;

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
import org.springframework.web.client.RestTemplate;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.dto.param.LoginParam;

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

	// @Autowired
	// private  RestTemplate restTemplate;

	private TestRestTemplate testRestTemplate = new TestRestTemplate();

	private RestTemplate restTemplate= new RestTemplate();

	@Autowired
    private UserInfoMapper userInfoMapper;

	@Autowired
	private UserConverter userConverter;


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
	void login(){

		try{
			String url = "http://localhost/user/login";
			// MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
			// map.add("userid", "");
			// map.add("password", "");
			LoginParam loginParam= new LoginParam();
			loginParam.setUserid(2019010101);
			loginParam.setPassword("1234");
			ResponseEntity responseEntity = restTemplate.postForEntity(url, loginParam, String.class);
			System.out.println(responseEntity.getBody());
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}


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
