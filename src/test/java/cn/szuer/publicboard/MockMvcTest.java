package cn.szuer.publicboard;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class MockMvcTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup()
    {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void admincheckuser() throws Exception{
      String url = "http://localhost/user/admin/checkuser?page=1&size=5";
      mockMvc.perform(MockMvcRequestBuilders.get(url)
              .accept(MediaType.APPLICATION_JSON))
              .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void addnews() throws Exception{

      String url = "/news/upload";
      File image = new File("/static/test.png");
      FileInputStream fileInputStream = new FileInputStream(image);
      MultipartFile multipartFile = new MockMultipartFile(image.getName(), fileInputStream);

    }

  
}
