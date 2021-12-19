package cn.szuer.publicboard;

import cn.szuer.publicboard.dto.param.RegisterParam;
import cn.szuer.publicboard.dto.param.SearchParam;
import cn.szuer.publicboard.reponse.BaseResponse;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlOnlineTest4
{
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
        HttpEntity<String> requEntity = new HttpEntity<String>(value,headers);
        //获得ResponseEntity， 包括响应体对象、响应头和响应状态， BaseResponse.class表明响应体的类型
        ResponseEntity<BaseResponse> responseEntity = template.postForEntity(url,requEntity,BaseResponse.class);
        cookies.add(responseEntity.getHeaders().get("set-cookie").get(0).toString());
        System.out.println(cookies);
    }

    /**
     * 测试搜索帖子
     */
    @Test
    public void TestSearchNews()
    {
        String url = "http://localhost:80/news/search";
        SearchParam searchParam= new SearchParam();
        ResponseEntity<BaseResponse> responseEntity;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity;
        //请求头添加cookie，用于传输Sessionid
        headers.put(HttpHeaders.COOKIE,cookies);

        /**
         *  测试用例1：只有关键字,搜索成功
         */
        searchParam.setKey("测试");
        System.out.println("测试用例1:"+searchParam);
        httpEntity = new HttpEntity<String>(JSON.toJSONString(searchParam), headers);
        responseEntity = template.exchange(url,HttpMethod.POST,httpEntity,BaseResponse.class);
        //assert测试
        //getStatusCode获得响应状态，该响应状态是HTTP自带的，并非服务端设置的
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        //getbody获得响应体，getCode获得相应体的状态码，getMsg获得响应体中的信息
        assertEquals(responseEntity.getBody().getCode(), 200);
        assertEquals(responseEntity.getBody().getMsg(), "搜索成功");

        /**
         *  测试用例2：参数有关键字,排序方式,帖子类型,关键字之间可以用空格分隔
         */
        searchParam.setKey("图片 测试");
        searchParam.setSort(2);
        searchParam.setType("校园卡丢失");
        System.out.println("测试用例2:"+searchParam);
        httpEntity = new HttpEntity<String>(JSON.toJSONString(searchParam), headers);
        responseEntity = template.exchange(url,HttpMethod.POST,httpEntity,BaseResponse.class);
        //assert测试
        //getStatusCode获得响应状态，该响应状态是HTTP自带的，并非服务端设置的
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        //getbody获得响应体，getCode获得相应体的状态码，getMsg获得响应体中的信息
        assertEquals(responseEntity.getBody().getCode(), 200);
        assertEquals(responseEntity.getBody().getMsg(), "搜索成功");

        /**
         *  测试用例3：参数有关键字,但没有相关的记录
         */
        searchParam.setKey("测试t图片");
        searchParam.setSort(2);
        searchParam.setType("校园卡丢失");
        System.out.println("测试用例3:"+searchParam);
        httpEntity = new HttpEntity<String>(JSON.toJSONString(searchParam), headers);
        responseEntity = template.exchange(url,HttpMethod.POST,httpEntity,BaseResponse.class);
        //assert测试
        //getStatusCode获得响应状态，该响应状态是HTTP自带的，并非服务端设置的
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        //getbody获得响应体，getCode获得相应体的状态码，getMsg获得响应体中的信息
        assertEquals(responseEntity.getBody().getCode(), 500);
        assertEquals(responseEntity.getBody().getMsg(), "没有相关的信息");

    }
}
