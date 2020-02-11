package com.site.blog.my.core.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;


public class UploadTest {

    AmazonS3 s3;
    String AWS_ACCESS_KEY = "AKIAJ22WRI3CTFVWGE7A"; // 【你的 access_key】
    String AWS_SECRET_KEY = "UXg0BS+2ADDI3g4AlUJMXzg3+Ogx6KO8z07w251G"; // 【你的 aws_secret_key】

    String bucketName = "jiweixiaomian"; // 【你 bucket 的名字】 # 首先需要保证 s3 上已经存在该存储桶

    {
        s3 = new AmazonS3Client(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY));
        s3.setRegion(Region.getRegion(Regions.US_EAST_1)); // 此处根据自己的 s3 地区位置改变
    }

    public String uploadToS3(File tempFile, String remoteFileName) {
        try {
            String bucketPath = bucketName + "/upload" ;
            s3.putObject(new PutObjectRequest(bucketPath, remoteFileName, tempFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, remoteFileName);
            URL url = s3.generatePresignedUrl(urlRequest);
            String picUrl = "https:"+url.getHost()+"/upload"+url.getPath();
            return picUrl;
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        }
        return null;
    }
}
