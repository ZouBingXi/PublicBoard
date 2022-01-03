package cn.szuer.publicboard;

import cn.szuer.publicboard.dto.param.AddCommentParam;
import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.dto.param.AddReplyParam;
import cn.szuer.publicboard.dto.param.LoginParam;
import cn.szuer.publicboard.dto.param.RegisterParam;

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

import com.alibaba.fastjson.JSON;
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
public class UrlOnlineTest_4_1 {

    private RestTemplate template = new RestTemplate();

    // 正常状态的cookie
    private List<String> cookies_well =new ArrayList<>();
    
    // 封禁用户的cookie
    private List<String> cookies_ban =new ArrayList<>();

    // 匿名用户的cookie
    private List<String> cookies_anony = new ArrayList<>();

    /**
     * 在其他单元测试前进行login操作，获得cookie所需的Sessionid
     * @throws IOException
     */
    @Before
    public void GetCookies() throws IOException
    {
        String url = "http://localhost/user/login";
        LoginParam loginParam = new LoginParam();


        /**
         * 获取正常用户的cookie
         */
        loginParam.setUserid(2019020202);
        loginParam.setPassword("123456789");        
        //获得ResponseEntity， 包括响应体对象、响应头和响应状态， BaseResponse.class表明响应体的类型
        ResponseEntity<BaseResponse> responseEntity = template.postForEntity(url, loginParam, BaseResponse.class);
        cookies_well.add(responseEntity.getHeaders().get("set-cookie").get(0).toString());

        /**
         * 获取封禁用户的cookie
         */
        loginParam.setUserid(2019040404);
        loginParam.setPassword("123456789");
        //获得ResponseEntity， 包括响应体对象、响应头和响应状态， BaseResponse.class表明响应体的类型
        ResponseEntity<BaseResponse> responseEntity2 = template.postForEntity(url, loginParam, BaseResponse.class);
        cookies_ban.add(responseEntity2.getHeaders().get("set-cookie").get(0).toString());

        /**
         * 获取匿名用户的cookie
         */
        loginParam.setUserid(2019050505);
        loginParam.setPassword("123456789");
        //获得ResponseEntity， 包括响应体对象、响应头和响应状态， BaseResponse.class表明响应体的类型
        ResponseEntity<BaseResponse> responseEntity3 = template.postForEntity(url, loginParam, BaseResponse.class);
        cookies_anony.add(responseEntity3.getHeaders().get("set-cookie").get(0).toString());

        // 打印查看
        System.out.println(cookies_well);
        System.out.println(cookies_ban);
        System.out.println(cookies_anony);

    }

    /**
     * 帖子评论接口测试
     */
    @Test
    public void testAddComment() throws IOException{
        try{

            String url = "http://localhost/news/comment";

            HttpHeaders headers = new HttpHeaders();
            //修改请求头的传输内容类型为json
            headers.setContentType(MediaType.APPLICATION_JSON);

            /**
             *  测试用例1：账号被禁用测试
             */

            //初始化编辑帖子参数对象
            AddCommentParam addCommentParam= new AddCommentParam();
            addCommentParam.setNewsid(1);
            addCommentParam.setContent("内容:测试帖子评论11");
            //在header写入封禁账号的cookie
            headers.put(HttpHeaders.COOKIE,cookies_ban);
            HttpEntity<String> httpEntity1 = new HttpEntity<>(JSON.toJSONString(addCommentParam), headers);
            System.out.println("测试用例1:"+ addCommentParam);
            ResponseEntity<BaseResponse> entity1 = template.exchange(url, HttpMethod.POST, httpEntity1, BaseResponse.class);
            //assert测试
            //getCode()获取返回的响应码，getMsg()获取返回的信息
            assertEquals(entity1.getBody().getCode(),500);
            assertEquals(entity1.getBody().getMsg(),"发布失败，当前账号被禁用！");

            /**
             *  测试用例2：用户为正常用户状态测试
             */

            addCommentParam.setNewsid(1);
            addCommentParam.setContent("内容:测试帖子评论22");
            //在header写入正常账号的cookie
            headers.put(HttpHeaders.COOKIE,cookies_well);
            HttpEntity<String> httpEntity2 = new HttpEntity<>(JSON.toJSONString(addCommentParam), headers);
            System.out.println("测试用例2:"+ addCommentParam);
            ResponseEntity<BaseResponse> entity2 = template.exchange(url, HttpMethod.POST, httpEntity2, BaseResponse.class);
            //assert测试
            //getCode()获取返回的响应码，getMsg()获取返回的信息
            assertEquals(entity2.getBody().getCode(),200);
            assertEquals(entity2.getBody().getMsg(),"发布评论成功！");

            /**
             *  测试用例3：用户为匿名用户状态测试
             */
            addCommentParam.setNewsid(1);
            addCommentParam.setContent("内容:测试帖子评论33");
            //在header写入正常账号的cookie
            headers.put(HttpHeaders.COOKIE,cookies_anony);
            HttpEntity<String> httpEntity3 = new HttpEntity<>(JSON.toJSONString(addCommentParam), headers);
            System.out.println("测试用例3:"+ addCommentParam);
            ResponseEntity<BaseResponse> entity3 = template.exchange(url, HttpMethod.POST, httpEntity3, BaseResponse.class);
            //assert测试
            //getCode()获取返回的响应码，getMsg()获取返回的信息
            assertEquals(entity3.getBody().getCode(),200);
            assertEquals(entity3.getBody().getMsg(),"匿名状态，发布评论成功！");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

     /**
     * 帖子回复接口测试
     */
    @Test
    public void testAddReplycookie() throws IOException{
        try{

            HttpHeaders headers = new HttpHeaders();
            //修改请求头的传输内容类型为json
            headers.setContentType(MediaType.APPLICATION_JSON);


            String url = "http://localhost/news/reply";        

            /**
             *  测试用例1：账号被禁用测试
             */
         
            //初始化编辑帖子参数对象
            AddReplyParam addReplyParam= new AddReplyParam();
            addReplyParam.setCommentid(1);
            addReplyParam.setContent("内容:测试帖子回复11");
            //在header写入封禁账号的cookie
            headers.put(HttpHeaders.COOKIE,cookies_ban);
            HttpEntity<String> httpEntity1 = new HttpEntity<>(JSON.toJSONString(addReplyParam), headers);
            System.out.println("测试用例1:"+ addReplyParam);
            ResponseEntity<BaseResponse> entity1 = template.exchange(url, HttpMethod.POST, httpEntity1, BaseResponse.class);
            //assert测试
            //getCode()获取返回的响应码，getMsg()获取返回的信息
            assertEquals(entity1.getBody().getCode(),500);
            assertEquals(entity1.getBody().getMsg(),"回复失败，当前账号被禁用！");

            /**
             *  测试用例2：用户为正常用户状态测试
             */

            addReplyParam.setCommentid(2);
            addReplyParam.setContent("内容:测试帖子回复22");
            //在header写入正常账号的cookie
            headers.put(HttpHeaders.COOKIE,cookies_well);
            HttpEntity<String> httpEntity2 = new HttpEntity<>(JSON.toJSONString(addReplyParam), headers);
            System.out.println("测试用例2:"+ addReplyParam);
            ResponseEntity<BaseResponse> entity2 = template.exchange(url, HttpMethod.POST, httpEntity2, BaseResponse.class);
            //assert测试
            //getCode()获取返回的响应码，getMsg()获取返回的信息
            assertEquals(entity2.getBody().getCode(),200);
            assertEquals(entity2.getBody().getMsg(),"发布回复成功！");

            /**
             *  测试用例3：用户为匿名用户状态测试
             */
            addReplyParam.setCommentid(3);
            addReplyParam.setContent("内容:测试帖子回复33");
            //在header写入正常账号的cookie
            headers.put(HttpHeaders.COOKIE,cookies_anony);
            HttpEntity<String> httpEntity3 = new HttpEntity<>(JSON.toJSONString(addReplyParam), headers);
            System.out.println("测试用例2:"+ addReplyParam);
            ResponseEntity<BaseResponse> entity3 = template.exchange(url, HttpMethod.POST, httpEntity3, BaseResponse.class);
            //assert测试
            //getCode()获取返回的响应码，getMsg()获取返回的信息
            assertEquals(entity3.getBody().getCode(),200);
            assertEquals(entity3.getBody().getMsg(),"匿名状态，发布回复成功！");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取帖子点赞单元测试
     */
    @Test
    public void testLikeNews() throws IOException{
        try{

            HttpHeaders headers = new HttpHeaders();
            //修改请求头的传输内容类型为json
            headers.setContentType(MediaType.APPLICATION_JSON);
            String url = "http://localhost/news/likenews";


            /**
             * 测试用例1：已点赞，取消点赞
             */
            //在header写入已点赞账号的cookie
            headers.put(HttpHeaders.COOKIE,cookies_well);
            Integer newsid = 1;     //点赞的帖子编号
            //修改http实体头
            HttpEntity<String> httpEntity1 = new HttpEntity<>( headers);
            System.out.println("测试用例1:"+ newsid);
            String url1 = url+"?newsid=1";
            ResponseEntity<BaseResponse> entity1 = template.exchange(url1, HttpMethod.GET, httpEntity1, BaseResponse.class);
            //assert测试
            //getCode()获取返回的响应码，getMsg()获取返回的信息
            assertEquals(entity1.getBody().getCode(),200);
            assertEquals(entity1.getBody().getMsg(),"取消点赞成功！");

            /**
             * 测试用例2：未点赞，进行点赞
             */
            //在header写入未点赞账号的cookie
            headers.put(HttpHeaders.COOKIE,cookies_anony);
            newsid = 1;     //点赞的帖子编号
            //修改http实体头
            HttpEntity<String> httpEntity2 = new HttpEntity<>(headers);
            System.out.println("测试用例2:"+ newsid);
            String url2 = url + "?newsid=1";
            ResponseEntity<BaseResponse> entity2 = template.exchange(url2, HttpMethod.GET, httpEntity2, BaseResponse.class);
            //assert测试
            //getCode()获取返回的响应码，getMsg()获取返回的信息
            assertEquals(entity2.getBody().getCode(),200);
            assertEquals(entity2.getBody().getMsg(),"点赞成功！");


        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查看帖子详情接口单元测试
     */
    @Test
    public void testViewNewsDetails() throws IOException{
        try{

            HttpHeaders headers = new HttpHeaders();
            //修改请求头的传输内容类型为json
            headers.setContentType(MediaType.APPLICATION_JSON);
            String url = "http://localhost/news/view";

            /**
             * 测试用例1：用户被禁用，获取失败
             */
            //在header写入禁用账号的cookie
            headers.put(HttpHeaders.COOKIE,cookies_ban);
            Integer newsid = 1;     //查看详情的帖子编号
            //修改http实体头
            HttpEntity<String> httpEntity1 = new HttpEntity<>(headers);
            System.out.println("测试用例1:"+ newsid);
            String url1 = url+"?newsid=1";
            ResponseEntity<BaseResponse> entity1 = template.exchange(url1, HttpMethod.GET, httpEntity1, BaseResponse.class);
            //assert测试
            //getCode()获取返回的响应码，getMsg()获取返回的信息
            assertEquals(entity1.getBody().getCode(),500);
            assertEquals(entity1.getBody().getMsg(),"获取失败,账号处于封禁状态!");

            /**
             * 测试用例2：获取成功
             */
            //在header写入正常账号的cookie
            headers.put(HttpHeaders.COOKIE,cookies_well);
            newsid = 1;     //查看详情的帖子编号
            //修改http实体头
            HttpEntity<String> httpEntity2 = new HttpEntity<>(headers);
            System.out.println("测试用例2:"+ newsid);
            String url2 = url + "?newsid=1";
            ResponseEntity<BaseResponse> entity2 = template.exchange(url2, HttpMethod.GET, httpEntity2, BaseResponse.class);
            //assert测试
            //getCode()获取返回的响应码，getMsg()获取返回的信息
            assertEquals(entity2.getBody().getCode(),200);
            assertEquals(entity2.getBody().getMsg(),"获取成功！");

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
            System.out.println(cookies_well);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies_well);
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            String url;
            /**
             * 测试用例1：该类型没有记录，获取失败
             */
            //合成get请求url
            url = "http://localhost/news/viewDiffNews?typeid=7&page=1&size=5";

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
            System.out.println(cookies_well);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies_well);
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



