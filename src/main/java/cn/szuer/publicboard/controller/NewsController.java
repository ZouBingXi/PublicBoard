package cn.szuer.publicboard.controller;

import java.util.List;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.utils.MinioUtil;
import cn.szuer.publicboard.utils.mapsturctconverter.NewsConverter;
import com.github.pagehelper.PageInfo;

import cn.szuer.publicboard.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.model.NewsInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.NewsService;


/**
 * 帖子管理
 */
@RestController
@RequestMapping("/news")
public class NewsController
{
    @Autowired
    NewsService NewsService;

    @Autowired
    MinioUtil minioUtil;
    /**
     * 查看帖子表
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("/admin/checkNews")
    public BaseResponse<PageInfo<NewsSendDto>> showNews(@RequestParam(name = "page",required = true,defaultValue = "1") int pageNum,
                                                        @RequestParam(name = "size",required = true,defaultValue = "10")int pageSize)
    {
        return new BaseResponse<PageInfo<NewsSendDto>>(200, "success", NewsService.getByPage(pageNum, pageSize));
    }

    /**
     * 编辑帖子
     * @param addNewsParam
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<NewsSendDto> add(@RequestBody AddNewsParam addNewsParam)
    {
        int res = NewsService.add(addNewsParam);

        if(res==21) {
            return new BaseResponse(500, "发布失败!当前账号被禁用!");
        }
        else if(res==22) {
            return new BaseResponse(500, "发布失败!帖子类型被禁用!");
        }
        else if(res==11) {
            return new BaseResponse(200, "发布成功！");
        }
        else if(res==12) {
            return new BaseResponse(200, "发布成功，使用匿名账号！");
        }

        return new BaseResponse(500, "发送失败，未知错误！");
    }

     /**
      * 发布帖子带图片
      * @param multipartFiles
      * @param addNewsParam
      * @return
      */
    @PostMapping("/addwithimages")
    public BaseResponse addWithImages(@RequestParam(name = "image", required = false) List<MultipartFile> multipartFiles ,AddNewsParam addNewsParam)
    {
        int res = NewsService.addWithImages(multipartFiles, addNewsParam);

        if(res==21) {
            return new BaseResponse(500, "发布失败!当前账号被禁用!");
        }
        else if(res==9001) {
            return new BaseResponse(500, "图片上传失败!");
        }
        else if(res==11) {
            return new BaseResponse(200, "发布成功！");
        }
        else if(res==12) {
            return new BaseResponse(200, "发布成功，使用匿名账号！");
        }

        return new BaseResponse(500, "发送失败，未知错误！");
    }

}