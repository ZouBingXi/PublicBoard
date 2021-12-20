package cn.szuer.publicboard.utils.mapsturctconverter;

import cn.szuer.publicboard.mapper.NewsImageMapper;
import cn.szuer.publicboard.mapper.NewsTypeMapper;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.NewsImage;
import cn.szuer.publicboard.model.NewsImageExample;
import cn.szuer.publicboard.utils.MinioUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsFormat {

    @Autowired(required = false)
    private NewsTypeMapper newsTypeMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private NewsImageMapper newsImageMapper;

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 将帖子类型编号转换成类型名
     * @param newstype
     * @return
     */
    public String idToname(Integer newstype)
    {
        return newsTypeMapper.selectByPrimaryKey(newstype).getTypename();
    }

     /**
     * 将用户id转成用户名
     * @param userid
     * @return
     */
    @Named("useridTousername")
    public String useridTousername(Integer userid)
    {
        return userInfoMapper.selectByPrimaryKey(userid).getUsername();
    }

    /**
     * 将用户id转成头像url
     * @param userid
     * @return
     */
    @Named("useridToheadimg")
    public String useridToheadimg(Integer userid) throws Exception
    {
        return minioUtil.getDownloadUrl(userInfoMapper.selectByPrimaryKey(userid).getHeadimage(), "avatar");
    }

    /**
     * 将帖子id转成图片list
     * @param newsid
     * @return
     */
    @Named("newsidToimgUrls")
    public List<String> newsidToimgUrls(Integer newsid) throws Exception
    {

        NewsImageExample example = new NewsImageExample();
        NewsImageExample.Criteria criteria = example.createCriteria();
        criteria.andNewsidEqualTo(newsid);
        List<NewsImage> imgs = newsImageMapper.selectByExample(example);
        List<String> uuids  = new ArrayList<>();
        for (NewsImage img:imgs)
        {
            uuids.add(img.getImageuuid());
        }
        List<String> imgList = new ArrayList<>();
        imgList = minioUtil.getDownloadUrls(uuids,"news");
        return imgList;
    }

     /**
     * 将日期转成字符串
     * @param sendtime
     * @return
     */
    public String DateToString(Date sendtime)
    {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(sendtime);
    }


}
