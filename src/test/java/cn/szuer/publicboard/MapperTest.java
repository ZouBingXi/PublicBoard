package cn.szuer.publicboard;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.szuer.publicboard.mapper.NewsInfoMapper;
import cn.szuer.publicboard.mapper.SubjectInfoMapper;
import cn.szuer.publicboard.model.NewsInfo;
import cn.szuer.publicboard.model.SubjectInfo;
import cn.szuer.publicboard.service.SubjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private SubjectInfoMapper subjectInfoMapper;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private NewsInfoMapper newsInfoMapper;
    

    @Test
    public void SubjectInfoMapperTest()
    {
      SubjectInfo subjectInfo= new SubjectInfo();
			subjectInfo.setUserid(2019010101);
			subjectInfo.setSubjecttitle("标题：测试话题");
			subjectInfo.setContent("内容：测试话题");
			subjectInfo.setSubjecttypeid(1);
      subjectInfo.setSendtime(new Date());
      subjectInfo.setViewnum(0);
      subjectInfo.setLikenum(0);
      subjectInfo.setTopstate(Boolean.FALSE);
      subjectInfo.setHotstate(Boolean.FALSE);
      subjectInfo.setExaminestate(Boolean.FALSE);
      subjectInfo.setAnonymousstate(false);
      System.out.println(subjectInfo.getSubjectid());
      System.out.println("--------------------------------------------");
			subjectInfoMapper.insertSelective(subjectInfo);
      System.out.println(subjectInfo.getSubjectid());

    }

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
