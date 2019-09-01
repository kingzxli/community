//package life.majiang.community.provider;
//
//import java.io.InputStream;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import cn.ucloud.ufile.UfileClient;
//import cn.ucloud.ufile.api.object.ObjectConfig;
//import cn.ucloud.ufile.auth.ObjectAuthorization;
//import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
//import cn.ucloud.ufile.bean.PutObjectResultBean;
//
//@Service
//public class UcloudProvider {
//	
//	@Value("${ucloud.ufile.public-key}")
//	private String publicKey;
//	
//	@Value("${ucloud.ufile.public-key}")
//	private String privateKey;
//	
//	public String upload(InputStream fileStream,String mimeType,String fileName) {
//		String generatedFileName;
//		String[] filePaths=fileName.split("\\.");
//		if(filePaths.length>1) {
//			generatedFileName=UUID.randomUUID().toString()+"."+filePaths[filePaths.length-1];
//		}else {
//			return null;
//		}
//		
//		try {
//			ObjectAuthorization objectAuthorization=new UfileObjectLocalAuthorization(publicKey, privateKey);
//			ObjectConfig config=new ObjectConfig("cn-gd","ufileos.com");
//			PutObjectResultBean response=UfileClient.object(objectAuthorization, config)
//					.putObject(fileStream, mimeType)
//					.nameAs(generatedFileName)
//					.toBucket("kingzx")
//					.setOnProgressListener((bytesWritten,contentLength) ->{
//						
//					}).execute();
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//		return generatedFileName;
//		
//	}
//	 
//
//}
