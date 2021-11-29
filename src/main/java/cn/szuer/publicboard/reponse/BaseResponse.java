package cn.szuer.publicboard.reponse;

import java.io.Serializable;
import java.util.Date;

public class BaseResponse<T> implements Serializable
{
    private static final long serialVersionUID = -1697733640925263374L;

    /**
     * 成功标志
     * @mock true
     */
    boolean success;
    /**
     * 状态码
     * @mock 200
     */
    private int code;
    /**
     * 处理信息
     * @mock success
     */
    private String msg;
    /**
     * 处理数据
     */
    private T data;
    /**
     * 时间戳
     */
    private Date timestamp = new Date();

    public BaseResponse()
    {
        timestamp=new Date();
    }

    public BaseResponse(int code)
    {
        this.code = code;
    }

    public BaseResponse(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(int code, String msg, T data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BaseResponse ok()
    {
        return new BaseResponse(200,"success");
    }
    public static BaseResponse ok(String msg)
    {
        return new BaseResponse(200,msg);
    }
    public static <T> BaseResponse<T> ok(T data)
    {
        return new BaseResponse(200,"success",data);
    }

    public static BaseResponse error()
    {
        return new BaseResponse(500,"error");
    }

    public static BaseResponse error(String msg)
    {
        return new BaseResponse(500,msg);
    }

    //帖子、话题错误响应
    //begin
    public static <T> BaseResponse<T> TitleTooLongError(T newsData)
    {
        return new BaseResponse(510,"标题过长!",newsData);
    }

    public static <T> BaseResponse<T> TitleTooShortError(T newsData)
    {
        return new BaseResponse(511,"标题不能为空!",newsData);
    }

    public static <T> BaseResponse<T> ContentTooLongError(T newsData)
    {
        return new BaseResponse(512,"内容过长!请检查文章字数是否在1000字内!",newsData);
    }

    public static <T> BaseResponse<T> ContentTooShortError(T newsData)
    {
        return new BaseResponse(513,"内容不能为空!",newsData);
    }
    //end

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}