package cn.szuer.publicboard;

import cn.szuer.publicboard.mapper.NewsInfoMapper;
import cn.szuer.publicboard.model.NewsInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {


    @Autowired
    private NewsInfoMapper newsInfoMapper;
  

    @Test
    public void NewsInfoMapperTest()
    {
      NewsInfo newsInfo= new NewsInfo();
      newsInfo.setUserid(2019010101);
      newsInfo.setNewstitle("标题：测试帖子");
      newsInfo.setContent("内容：测试帖子");
      newsInfo.setNewstypeid(3);

      newsInfo.setSendtime(new Date());
      newsInfo.setViewnum(0);
      newsInfo.setLikenum(0);
      newsInfo.setTopstate(Boolean.FALSE);
      newsInfo.setHotstate(Boolean.FALSE);
      newsInfo.setExaminestate(Boolean.FALSE);
      newsInfo.setAnonymousstate(true);
      
			newsInfoMapper.insertSelective(newsInfo);
      System.out.println(newsInfo.getNewsid());

    }

  
}
