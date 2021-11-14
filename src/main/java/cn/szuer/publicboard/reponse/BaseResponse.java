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