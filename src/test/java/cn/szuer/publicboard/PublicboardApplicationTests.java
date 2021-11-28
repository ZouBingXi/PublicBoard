package cn.szuer.publicboard;

import java.util.ArrayList;
import java.util.List;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.dto.param.AddSubjectParam;
import cn.szuer.publicboard.mapper.*;
import cn.szuer.publicboard.model.*;
import cn.szuer.publicboard.service.NewsService;
import cn.szuer.publicboard.service.SubjectService;
import cn.szuer.publicboard.utils.mapsturctconverter.NewsConverter;
import cn.szuer.publicboard.utils.mapsturctconverter.SubjectConverter;
import com.github.pagehelper.PageInfo;

// import com.github.pagehelper.PageInfo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.dto.param.LoginParam;

import cn.szuer.publicboard.service.UserService;
import cn.szuer.publicboard.utils.mapsturctconverter.UserConverter;


@RunWith(SpringRunner.class) 
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublicboardApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private NewsService newsService;
	// @Autowired
	// private  RestTemplate restTemplate;

	private TestRestTemplate testRestTemplate = new TestRestTemplate();

	private RestTemplate restTemplate= new RestTemplate();

	@Autowired(required=false)
    private UserInfoMapper userInfoMapper;

	@Autowired(required=false)
	private SubjectInfoMapper subjectInfoMapper;
	@Autowired(required=false)
	private NewsInfoMapper newsInfoMapper;

	@Autowired(required=false)
	SubjectTypeMapper subjectTypeMapper = null;

	@Autowired(required=false)
	NewsTypeMapper newsTypeMapper = null;

	@Autowired(required=false)
	SubjectStateMapper subjectStateMapper = null;
	@Autowired(required=false)
	NewsStateMapper newsStateMapper = null;

	@Autowired
	private UserConverter userConverter;

	@Autowired(required=false)
	private SubjectConverter subjectConverter;

	@Autowired(required=false)
	private NewsConverter newsConverter;


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

	@Test
	void testSubjectService(){

		List<SubjectSendDto> list = subjectService.getAll();
		System.out.println(list);
		PageInfo<SubjectSendDto> pageInfo = subjectService.getByPage(1, 3);
		System.out.println(pageInfo);
	}

	@Test
	//测试编辑话题功能
	void addSubject(){
		try{
			String url = "http://localhost/subject/add";
			//初始化测试用例1
			AddSubjectParam addSubjectParam= new AddSubjectParam();
			addSubjectParam.setUserid(2019010101);
			addSubjectParam.setSubjecttitle("标题：测试话题");
			addSubjectParam.setContent("内容：测试话题");
			addSubjectParam.setSubjecttype(1);

			ResponseEntity responseEntity = restTemplate.postForEntity(url, addSubjectParam, String.class);
			System.out.println(responseEntity.getBody());//打印测试结果

			//修改subjecttype，使subjecttype不存在
			addSubjectParam.setSubjecttype(10);
			ResponseEntity responseEntity1 = restTemplate.postForEntity(url, addSubjectParam, String.class);
			System.out.println(responseEntity1.getBody());//打印测试结果
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}


	@Test
	//测试话题mapper结构
	void testSubjectMapstruct()
	{
		SubjectInfo subjectInfo = subjectInfoMapper.selectByPrimaryKey(1);
		//调用convertor将addsubjectparam转换成dto
		SubjectSendDto subjectSendDto= subjectConverter.SubjectInfo2SubjectSendDto(subjectInfo);

		System.out.println(subjectSendDto.toString());

	}

	@Test
	void testNewsService(){

		List<NewsSendDto> list = newsService.getAll();
		System.out.println(list);
		PageInfo<NewsSendDto> pageInfo = newsService.getByPage(1, 3);
		System.out.println(pageInfo);
	}

	@Test
		//测试编辑帖子功能
	void addNews(){
		try{
			String url = "http://localhost/news/add";
			//初始化测试用例1
			AddNewsParam addNewsParam= new AddNewsParam();
			addNewsParam.setUserid(2019010101);
			addNewsParam.setNewstitle("标题：测试帖子");
			addNewsParam.setContent("内容：测试帖子");
			addNewsParam.setNewstype(1);

			ResponseEntity responseEntity = restTemplate.postForEntity(url, addNewsParam, String.class);
			System.out.println(responseEntity.getBody());//打印测试结果

			//修改newstype，使newstype不存在
			addNewsParam.setNewstype(10);
			ResponseEntity responseEntity1 = restTemplate.postForEntity(url, addNewsParam, String.class);
			System.out.println(responseEntity1.getBody());//打印测试结果
		}catch(Exception e)
		{
			e.printStackTrace();
		}

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





}

