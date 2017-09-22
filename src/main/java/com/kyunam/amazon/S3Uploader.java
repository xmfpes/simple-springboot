package com.kyunam.amazon;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.MultiObjectDeleteException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Uploader {
	//private static AmazonS3 s3client = new AmazonS3Client(InstanceProfileCredentialsProvider.getInstance());
	private static AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
	private static ObjectMetadata meta;
	public static boolean sendFiles(String name, byte[] file) {
		InputStream is = new ByteArrayInputStream(file);
		try {
			s3client.putObject(makeRequest(is, name));
			return true;
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
	public static boolean deleteFiles(List<String> files) {
		DeleteObjectsRequest multiObjectDeleteRequest = new DeleteObjectsRequest("kyubucket");

		List<KeyVersion> keys = new ArrayList<KeyVersion>();
		for(String file : files){
			keys.add(new KeyVersion(file));
		}
		
		multiObjectDeleteRequest.setKeys(keys);

		try {
		    DeleteObjectsResult delObjRes = s3client.deleteObjects(multiObjectDeleteRequest);
		    System.out.format("Successfully deleted all the %s items.\n", delObjRes.getDeletedObjects().size());
		    return true;
		    			
		} catch (MultiObjectDeleteException e) {
		    // Process exception.
			return false;
		}
	}
	public static PutObjectRequest makeRequest(InputStream is, String fileName) {
		meta = new ObjectMetadata();
		meta.setContentType("image/png");
		return new PutObjectRequest("kyubucket", fileName, is, meta).withCannedAcl(CannedAccessControlList.PublicRead);
	}
}
