package utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class FileUpload {
	//���ú��˺ŵ�ACCESS_KEY��SECRET_KEY
	static String ACCESS_KEY = "rgOtSST7pPDCPJ9KRMhPYAvPMeZAnJ8PcHNgJkky";
	static String SECRET_KEY = "bE_AjzQPEGiSrGe0_hTYp49BODEowf912nz6eQYU";
	//Ҫ�ϴ��Ŀռ�
	static String bucketname = "schooltime";
	//����
	static String yuming = "http://7xo6s1.com1.z0.glb.clouddn.com/";		
	//��Կ����
	static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	//�����ϴ�����
	static UploadManager uploadManager = new UploadManager();
	//���ϴ���ʹ��Ĭ�ϲ��ԣ�ֻ��Ҫ�����ϴ��Ŀռ����Ϳ�����
	public static String getUpToken(){
	    return auth.uploadToken(bucketname);
	}
	
	public static String upload(String filepath){
		String path = null;
		try {
	      //����put�����ϴ�
	      Response res = uploadManager.put(filepath, null, getUpToken());
	      //��ӡ���ص���Ϣ      
	      path =yuming+ res.bodyString().substring(9, res.bodyString().indexOf(",")-1);       
	      } catch (QiniuException e) {
	          Response r = e.response;
	          // ����ʧ��ʱ��ӡ���쳣����Ϣ
	          System.out.println(r.toString());
	          try {
	              //��Ӧ���ı���Ϣ
	            System.out.println(r.bodyString());
	          } catch (QiniuException e1) {
	              //ignore
	          }
	    }
		return path;
	}
	
	
//	//�ϴ�����,����·��
//	public static String attachmentUpload(File fileAttachment ,String fileAttachmentContentType,String fileAttachmentFileName,String attachmentSavePath ) throws IOException{
//		//�ж��Ƿ���ڸ��ļ��У��������ڣ�����/upload�ļ���,�˴�������
//		File f = new  File(attachmentSavePath);	
//		if(!f.exists()){
//			f.mkdir();
//		}
//		if(fileAttachment!=null){
//			FileOutputStream fos=new FileOutputStream(f+"\\"+fileAttachmentFileName);
//			FileInputStream fis=new FileInputStream(fileAttachment);
//			byte [] buffer=new byte[1024];
//			int len=0;
//			while((len=fis.read(buffer))!=-1){
//				fos.write(buffer,0,len);
//			}
//			fis.close();
//			fos.close();
//		}
//		return f+"\\"+fileAttachmentFileName; //�ݴ���upload��
//	}
//	
//	//�ϴ�ͼƬ������·��
//	public static String pictureUpload(File filePicture) throws IOException{
//		String zoomPath=filePicture.getParentFile()+"\\f"+filePicture.getName();
//		File cutPicture = PictureZoom.cutImage(filePicture,new File(zoomPath));
//		File zoomPicture = PictureZoom.resize(cutPicture, new File(zoomPath), 300, 0, 1f);		
//		String token = Token.createToken(new Date().getTime()+180, 1142528 , "{\"height\":\"h\",\"width\":\"w\",\"s_url\":\"url\"}");
//		String json = PostImage.doUpload(zoomPicture, token);
//		String url=getUrl(json);
//		System.out.println(zoomPicture.delete());
//		return url;
//	}
//	
//	//��վ����ͼ��
//	public static String pictureWebUpload(File filePicture) throws IOException{ 
//		String zoomPath=filePicture.getParentFile()+"\\f"+filePicture.getName();
//		File cutPicture = PictureZoom.cutImageWeb(filePicture,new File(zoomPath));
//		File zoomPicture = PictureZoom.resize(cutPicture, new File(zoomPath), 300, 0, 1f);		
//		String token = Token.createToken(new Date().getTime()+180, 1142528 , "{\"height\":\"h\",\"width\":\"w\",\"s_url\":\"url\"}");
//		String json = PostImage.doUpload(zoomPicture, token);
//		String url=getUrl(json);
//		zoomPicture.delete();
//		return url;
//	}
//	
//	private static String getUrl(String json){
//		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(json);
//		return jsonObject.getString("url");
//	}
//	
	public static boolean pictureDel(String url) {
		//���urlΪnull
		if(url == null){			
		}	
		return true;
	}
		
	public static boolean attachmentDel(String url) {
		//���urlΪnull,˵���ûû�и��� 
		if(url == null) {			
		}		
		return true;
	}
}
