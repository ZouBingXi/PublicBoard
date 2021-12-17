package cn.szuer.publicboard;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.dto.param.RegisterParam;

import cn.szuer.publicboard.service.NewsService;
import com.github.pagehelper.PageInfo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import cn.szuer.publicboard.reponse.BaseResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlOnlineTest_3 {

    private RestTemplate template = new RestTemplate();

    private List<String> cookies =new ArrayList<>();

    /**
     * 在其他单元测试前进行login操作，获得cookie所需的Sessionid
     * @throws IOException
     */
    @Before
    public void GetCookies() throws IOException
    {
        String url = "http://localhost/user/login";

        //更改请求头Header, 修改MediaType为APPLICATION_JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> param = new HashMap<>();
        param.put("userid", "2019010101");
        param.put("password", "123456789");
        //请求体的参数，一定要转成String, 才能被接受
        String value = mapper.writeValueAsString(param);
        // System.out.println(value);

        //HttpEntity包含消息头和消息体
        HttpEntity<String> requEntity = new HttpEntity<String>(value, headers);
        //获得ResponseEntity， 包括响应体对象、响应头和响应状态， BaseResponse.class表明响应体的类型
        ResponseEntity<BaseResponse> responseEntity = template.postForEntity(url, requEntity, BaseResponse.class);
        cookies.add(responseEntity.getHeaders().get("set-cookie").get(0).toString());
        System.out.println(cookies);
    }

    /**
     * 查看帖子详情接口单元测试
     */
    @Test
    public void testViewNewsDetails() throws IOException{
        try{

            HttpHeaders headers = new HttpHeaders();
            System.out.println(cookies);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies);
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            String url;
            /**
             * 测试用例1：用户被禁用，获取失败
             */
            //合成get请求url
            url = "http://localhost/news/view?userid=2019040404&newsid=3";

            ResponseEntity<BaseResponse> entity1 = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            System.err.println(entity1.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity1.getStatusCode(), HttpStatus.OK);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity1.getBody().getMsg(), "获取失败！账号处于封禁状态");

            /**
             * 测试用例2：获取成功
             */
            url = "http://localhost/news/view?userid=2019010101&newsid=3";

            ResponseEntity<BaseResponse> entity2 = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            System.err.println(entity2.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity2.getStatusCode(), HttpStatus.OK);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity2.getBody().getMsg(), "获取成功！");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查看不同类型帖子接口单元测试
     */
    @Test
    public void testViewDiffNews() throws IOException{
        try{

            HttpHeaders headers = new HttpHeaders();
            System.out.println(cookies);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies);
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            String url;
            /**
             * 测试用例1：该类型没有记录，获取失败
             */
            //合成get请求url
            url = "http://localhost/news/viewDiffNews?typeid=3&page=1&size=5";

            ResponseEntity<BaseResponse> entity1 = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            //System.err.println(entity1.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity1.getBody().getCode(), 500);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity1.getBody().getMsg(), "该类型包含帖子数为0，获取失败！");

            /**
             * 测试用例2：获取成功
             */
            url = "http://localhost/news/viewDiffNews?typeid=1&page=1&size=5";

            ResponseEntity<BaseResponse> entity2 = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            System.err.println(entity2.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity2.getStatusCode(), HttpStatus.OK);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity2.getBody().getMsg(), "获取成功！");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取帖子类型单元测试
     */
    @Test
    public void testGetNewsType() throws IOException{
        try{

            HttpHeaders headers = new HttpHeaders();
            System.out.println(cookies);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies);
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            String url;
            /**
             * 测试用例1：获取成功
             */
            //合成get请求url
            url = "http://localhost/news/getNewsType";

            ResponseEntity<BaseResponse> entity1 = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            System.err.println(entity1.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity1.getBody().getCode(), 200);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity1.getBody().getMsg(), "获取成功！");


        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }



}
