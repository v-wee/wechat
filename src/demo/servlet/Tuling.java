package demo.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import demo.entity.ReceiveXmlEntity;
import demo.process.FormatXmlProcess;
import demo.process.ReceiveXmlProcess;
import demo.process.WechatProcess;

public class Tuling {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws ParseException, IOException, JSONException {
	     String INFO = URLEncoder.encode("热门电影", "utf-8"); 
	     String requesturl = "http://www.tuling123.com/openapi/api?key=4acf950948378220c92af8abea1ebde3&info="+INFO;
	     HttpGet request = new HttpGet(requesturl); 
	     HttpResponse response = new DefaultHttpClient().execute(request);
	     String result = "";
	     //200即正确的返回码 
	     if(response.getStatusLine().getStatusCode()==200){ 
	         result = EntityUtils.toString(response.getEntity()); 
	         System.out.println("返回结果："+result); 
	}
	     
			JSONObject json = new JSONObject(result);
			FormatXmlProcess fxp = new FormatXmlProcess();
			String wee = fxp.formatNewsAnswer("v6973821", "wee-studio", json);
			
			System.out.println(wee);
	}
	
	public void test(){
		
		String xml = "<xml><URL><![CDATA[http://1.weechat.sinaapp.com/wechat.do]]></URL><ToUserName><![CDATA[wee-studio]]></ToUserName><FromUserName><![CDATA[v6973821]]></FromUserName><CreateTime>123456</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你是谁]]></Content><MsgId>456464</MsgId></xml>";


		ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml.trim());
		String comPresult = new FormatXmlProcess().formatTextAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), "中国山东找蓝翔");
		System.out.println(comPresult);
		

		StringBuffer sb1 = new StringBuffer();
		sb1.append("<xml><ToUserName><![CDATA[v6973821]]></ToUserName><FromUserName><![CDATA[wee-studio]]></FromUserName><CreateTime>");
		sb1.append(new Date().getTime());
		sb1.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[我是棒棒哒魏大宝]]></Content></xml>");
		
		String result = new WechatProcess().processWechatMag(xml);
		System.out.println(result);
		
	}
}
