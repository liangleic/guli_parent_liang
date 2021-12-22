package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtil;
import lombok.val;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Description: OssServiceImpl$
 * @Author liang
 * @Date: 2021/11/14$ 22:30$
 * @Version 1.0
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取文件名称
            String filename = file.getOriginalFilename();
            //在文件名中添加唯一值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filename = uuid+filename;

            //把文件按照日期进行分类
            //获取当前日期
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            filename = dataPath+"/"+filename;

            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();

            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传到阿里云oss路径手动拼接出来
            String url = "https://" + bucketName + "." + endpoint + "/" + filename;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }
}
