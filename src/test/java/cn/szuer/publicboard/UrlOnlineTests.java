package cn.szuer.publicboard;

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
import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import cn.szuer.publicboard.reponse.BaseResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlOnlineTests {
    
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
        param.put("password", "1234");
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
     * getForObject方法只返回响应体对象，没有Http响应状态码等信息
     */
    @Test
    public void testgetAll(){
        try{
            String url = "http://localhost/user/all";
            BaseResponse response = template.getForObject(url, BaseResponse.class);
            System.err.println(response);
            assertEquals(response.getMsg(), "success");

         }catch(Exception e){
             e.printStackTrace();
        }
    }


    /**
     * getForEntity方法只返回的包括响应体对象、响应头和响应状态，
     */
    @Test
    public void testgetAll2(){
        try{
            String url = "http://localhost/user/all";
            ResponseEntity<BaseResponse> entity = template.getForEntity(url, BaseResponse.class);
            // HttpStatus code = entity.getStatusCode();
            // System.err.println(code.toString());
            // System.err.println(entity.getBody().getCode());
            assertEquals(entity.getStatusCode(), HttpStatus.OK);
            assertTrue(entity.getBody().getMsg().equals("success"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Rollback
    public void testgetByPage()
    {
        try{

            HttpHeaders headers = new HttpHeaders();
            // List<String> cookies =new ArrayList<>();
            //cookies.add("JSESSIONID=FEA9AE832F68F7A0F1AC5D52D60AC841; Path=/; HttpOnly");
            System.out.println(cookies);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies); 
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            String url = "http://localhost/user/admin/checkuser?page=1&size=5";
            // ResponseEntity<String> entity = template.getForEntity(url, String.class);
            ResponseEntity<BaseResponse> entity = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            // HttpStatus code = entity.getStatusCode();
            // System.err.println(code);
            System.err.println(entity.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity.getStatusCode(), HttpStatus.OK);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity.getBody().getMsg(), "success");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 表单提交，接口入参“没有”@requestbody注解
     */
    @Test
    public void testLoginbyForm(){
        try{
            //默认为Form表单提交
            String url = "http://localhost/user/login";
            //Post以Form表单方式提交必须用LinkedMultiValueMap
            LinkedMultiValueMap<String, Integer> param = new LinkedMultiValueMap<>();
            param.add("userid", 2019010101);
            param.add("password", 1234);
            //获得ResponseEntity， 包括响应体对象、响应头和响应状态， String.class表明响应体被转化为String类型
            ResponseEntity<String> ResponseEntity = template.postForEntity(url, param, String.class);
            HttpStatus code = ResponseEntity.getStatusCode();
            System.err.println(code);
            System.err.println(ResponseEntity.toString());
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * payload提交，接口入参“有”@requestbody
     * 若controller使用了@requestbody注解，
     * 则前端的提交方式变为Payload,需要修改Header中的ContentType,
     * 否则会报unsupported Media Type 
    **/
    @Test
    public void testLoginbyPayload() throws IOException{
    

        try{
            //接口的url
            String url = "http://localhost/user/login";

            //更改请求头Header, 修改MediaType为APPLICATION_JSON
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> param = new HashMap<>();
            param.put("userid", "2019010101");
            param.put("password", "1234");
            //请求体的参数，一定要转成String, 才能被接受
            String value = mapper.writeValueAsString(param);
            System.out.println(value);

            //HttpEntity包含消息头和消息体
            HttpEntity<String> requEntity = new HttpEntity<String>(value, headers);

            //获得ResponseEntity， 包括响应体对象、响应头和响应状态， BaseResponse.class表明响应体的类型
            ResponseEntity<BaseResponse> responseEntity = template.postForEntity(url, requEntity, BaseResponse.class);

            // System.out.println(responseEntity.toString());
            System.out.println(responseEntity.getHeaders().get("Set-Cookie").get(0));
            cookies.add(responseEntity.getHeaders().get("Set-Cookie").get(0).toString());

            //assert测试 
            //getStatusCode获得响应状态，该响应状态是HTTP自带的，并非服务端设置的
            assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
            //getbody获得响应体，getMsg获得响应体中的信息
            assertEquals(responseEntity.getBody().getMsg(), "登录成功");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Test
    @Rollback
    public void testgetByPage1()
    {
        try{

            HttpHeaders headers = new HttpHeaders();
            // List<String> cookies =new ArrayList<>();
            //cookies.add("JSESSIONID=FEA9AE832F68F7A0F1AC5D52D60AC841; Path=/; HttpOnly");
            System.out.println(cookies);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies);
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            String url = "http://localhost/subject/admin/checksubject?page=1&size=5";
            // ResponseEntity<String> entity = template.getForEntity(url, String.class);
            ResponseEntity<BaseResponse> entity = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            // HttpStatus code = entity.getStatusCode();
            // System.err.println(code);
            System.err.println(entity.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity.getStatusCode(), HttpStatus.OK);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity.getBody().getMsg(), "success");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 表单提交，接口入参“没有”@requestbody注解
     */
    @Test
    public void testAddsubjectbyForm1(){
        try{
            //默认为Form表单提交
            String url = "http://localhost/subject/add";
            //Post以Form表单方式提交必须用LinkedMultiValueMap
            LinkedMultiValueMap<String, String > param = new LinkedMultiValueMap<>();
            param.add("userid", "2019010101");
            param.add("title", "话题标题");
            param.add("content","话题内容");
            param.add("subjecttype","1");
            //获得ResponseEntity， 包括响应体对象、响应头和响应状态， String.class表明响应体被转化为String类型
            ResponseEntity<String> ResponseEntity = template.postForEntity(url, param, String.class);
            HttpStatus code = ResponseEntity.getStatusCode();
            System.err.println(code);
            System.err.println(ResponseEntity.toString());
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * payload提交，接口入参“有”@requestbody
     * 若controller使用了@requestbody注解，
     * 则前端的提交方式变为Payload,需要修改Header中的ContentType,
     * 否则会报unsupported Media Type
     **/
    @Test
    public void testAddsubjectbyPayload1() throws IOException{


        try{
            //接口的url
            String url = "http://localhost/subject/add";

            //更改请求头Header, 修改MediaType为APPLICATION_JSON
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> param = new HashMap<>();
            param.put("userid", "2019010101");
            param.put("title", "话题标题");
            param.put("content","话题内容");
            param.put("subjecttype","1");
            //请求体的参数，一定要转成String, 才能被接受
            String value = mapper.writeValueAsString(param);
            System.out.println(value);

            //HttpEntity包含消息头和消息体
            HttpEntity<String> requEntity = new HttpEntity<String>(value, headers);

            //获得ResponseEntity， 包括响应体对象、响应头和响应状态， BaseResponse.class表明响应体的类型
            ResponseEntity<BaseResponse> responseEntity = template.postForEntity(url, requEntity, BaseResponse.class);

            // System.out.println(responseEntity.toString());
            System.out.println(responseEntity.getHeaders().get("Set-Cookie").get(0));
            cookies.add(responseEntity.getHeaders().get("Set-Cookie").get(0).toString());

            //assert测试
            //getStatusCode获得响应状态，该响应状态是HTTP自带的，并非服务端设置的
            assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
            //getbody获得响应体，getMsg获得响应体中的信息
            assertEquals(responseEntity.getBody().getMsg(), "发布成功！");
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
