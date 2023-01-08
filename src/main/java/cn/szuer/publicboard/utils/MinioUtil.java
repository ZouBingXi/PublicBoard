package cn.szuer.publicboard.utils;


import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;


import cn.szuer.publicboard.reponse.BaseResponse;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;

import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.SneakyThrows;



/**
 * 用于上传图片到服务器的工具类
 */
@Component
public class MinioUtil {
  
    /**
     * minio用户ID
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * minio密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;


    /**
     * IP+端口
     */
    @Value("${minio.endPoint}")
    private String endPoint;

    /**
     * 存储桶名
     */
    @Value("${minio.bucket}")
    private String bucketName;


    private static MinioClient instance ;
    public MinioClient getInstance() throws InvalidPortException, InvalidEndpointException {
        if (instance == null) {
            instance = new MinioClient(endPoint,accessKey,secretKey);
        }
        return instance;
        
    }
        
    @SneakyThrows
    public BaseResponse<String> uploadAvatar(MultipartFile multipartFile, String path){

        try{
              //使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
              MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
              // 检查存储桶是否已经存在
              boolean isExist = minioClient.bucketExists(bucketName);
              //若不存在创建一个名为{bucketName}的存储桶，用于存储图片
              if(!isExist){
                minioClient.makeBucket(bucketName);
           }
            //从multipartFile中获取文件名
            String fileName = multipartFile.getOriginalFilename();
            //使用java提供的工具类生成UUID,并和path以及文件名后缀拼接成objectname
            String objectName = path +"/"+ UUID.randomUUID().toString().replace("-", "")+ fileName.substring(fileName.lastIndexOf("."));
            //设置putObjectOptions, 上传对象可选对象，设置对象大小等
            PutObjectOptions putObjectOptions = new PutObjectOptions(multipartFile.getSize(), PutObjectOptions.MIN_MULTIPART_SIZE);
            //设置上传内容类型
            putObjectOptions.setContentType(multipartFile.getContentType());
            // 使用putObject上传单张图片到存储桶中
            minioClient.putObject(bucketName, objectName, multipartFile.getInputStream(), putObjectOptions);
            //将uuid+图片后缀 写入response返回
            return new BaseResponse<String>(9000,  "上传成功",  objectName.split("/")[1]);
          
        }catch(MinioException e ) {
           System.out.println("Error occurred: " + e);
           return new BaseResponse<String>(9001, "上传失败",  null);
        }

    }

    @SneakyThrows
    public BaseResponse<List<String>> uploadFile(List<MultipartFile> multipartFiles, String path){
        try{

           //使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
           MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
            // 检查存储桶是否已经存在
           boolean isExist = minioClient.bucketExists(bucketName);
           List<String> objectNameStrings = new ArrayList<>();
           if(!isExist){
             minioClient.makeBucket(bucketName);
           }
           for(MultipartFile multipartFile:multipartFiles)
           {
              String fileName = multipartFile.getOriginalFilename();
              String objectName = path +"/"+ UUID.randomUUID().toString().replace("-", "")+ fileName.substring(fileName.lastIndexOf("."));
              objectNameStrings.add(objectName.split("/")[1]);
              PutObjectOptions putObjectOptions = new PutObjectOptions(multipartFile.getSize(), PutObjectOptions.MIN_MULTIPART_SIZE);
              putObjectOptions.setContentType(multipartFile.getContentType());
              minioClient.putObject(bucketName, objectName, multipartFile.getInputStream(), putObjectOptions);
           }
           return new BaseResponse<List<String>>(9000,  "上传成功",  objectNameStrings);
          
        }catch(MinioException e ) {
           System.out.println("Error occurred: " + e);
           return new BaseResponse(9001, "上传失败",  null);
        }
    }


    /**
     * 获取单张图片url
     * @param uuid
     * @return
     * @throws Exception
     */
    public String getDownloadUrl(String uuid,String path) throws Exception {
        return endPoint+bucketName+"/"+path+"/"+uuid;
    }

    /**
     * 批量获取图片url
     * @param uuids
     * @return
     * @throws Exception
     */
    public List<String> getDownloadUrls(List<String> uuids,String path) throws Exception {

        List<String> urlList=new ArrayList<>();

        //通过imgName从服务器获取下载url
        for (String uuid:uuids) {

            //获取图片下载url
            String url = endPoint+bucketName+"/"+path+"/"+uuid;
            urlList.add(url);
        }
        return urlList;
    }

    /**
     * 获取默认头像
     * @param uuid
     * @return
     */
    public String getDefaultAvatar(){
      return endPoint + bucketName + "/avatar/DefaultAvatar.jpg";
    }

}
