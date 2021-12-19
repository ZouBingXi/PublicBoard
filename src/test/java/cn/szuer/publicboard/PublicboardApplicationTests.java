package cn.szuer.publicboard;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.dto.param.LoginParam;
import cn.szuer.publicboard.mapper.NewsInfoMapper;
import cn.szuer.publicboard.mapper.NewsTypeMapper;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.NewsInfo;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.service.NewsService;
import cn.szuer.publicboard.service.UserService;
import cn.szuer.publicboard.utils.AuthenticationUtil;
import cn.szuer.publicboard.utils.mapsturctconverter.NewsConverter;
import cn.szuer.publicboard.utils.mapsturctconverter.UserConverter;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublicboardApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private NewsService newsService;

	// @Autowired
	// private  RestTemplate restTemplate;

	private TestRestTemplate testRestTemplate = new TestRestTemplate();

	private RestTemplate restTemplate= new RestTemplate();

	@Autowired(required=false)
	private UserInfoMapper userInfoMapper;

	@Autowired(required=false)
	private NewsInfoMapper newsInfoMapper;


	@Autowired(required=false)
	NewsTypeMapper newsTypeMapper = null;

	@Autowired
	private UserConverter userConverter;

	@Autowired(required=false)
	private NewsConverter newsConverter;

  @Autowired
  private AuthenticationUtil authenticationUtil;


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
			ResponseEntity responseEntity = restTemplate.postForEntity(url,loginParam,String.class);
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


	@Test
		//测试帖子mapper结构
	void testNewsMapstruct()
	{
		NewsInfo newsInfo = newsInfoMapper.selectByPrimaryKey(1);
		//调用convertor将addnewsparam转换成dto
		NewsSendDto newsSendDto= newsConverter.NewsInfo2NewsSendDto(newsInfo);

		System.out.println(newsSendDto.toString());

	}

	@Test
	void updateEncodeUser()
	{
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		UserInfo user1=userInfoMapper.selectByPrimaryKey(2019010101);
		user1.setPassword(encoder.encode("123456789"));
		userInfoMapper.updateByPrimaryKey(user1);

		UserInfo user2=userInfoMapper.selectByPrimaryKey(2019020202);
		user2.setPassword(encoder.encode("123456789"));
		userInfoMapper.updateByPrimaryKey(user2);

		UserInfo user3=userInfoMapper.selectByPrimaryKey(2019030303);
		user3.setPassword(encoder.encode("123456789"));
		userInfoMapper.updateByPrimaryKey(user3);

		UserInfo user4=userInfoMapper.selectByPrimaryKey(2019040404);
		user4.setPassword(encoder.encode("123456789"));
		userInfoMapper.updateByPrimaryKey(user4);

		UserInfo user5=userInfoMapper.selectByPrimaryKey(2019050505);
		user5.setPassword(encoder.encode("123456789"));
		userInfoMapper.updateByPrimaryKey(user5);
	}

}

