package demo.process;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import demo.entity.ReceiveXmlEntity;
/**
 * 封装最终的xml格式结果
 *
 */
public class FormatXmlProcess {
	/**
	 * 封装文字类的返回消息
	 * @param to
	 * @param from
	 * @param content
	 * @return
	 */
	public String formatTextAnswer(String to, String from, String content) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(to);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(from);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(date.getTime());
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(content);
		sb.append("]]></Content></xml>");
		return sb.toString();
	}
	
	//TODO 封装新闻类数据
	public String formatNewsAnswer(String to, String from, JSONObject json) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		String url = "";
		String title = "";
		try {
			url = json.getString("url");
			title = json.getString("text");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(to);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(from);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(date.getTime());
		sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA[");
		sb.append(title);
		sb.append("]]></Title><Url><![CDATA[");
		sb.append(url);
		sb.append("]]></Url></item></Articles></xml> ");
		
		return sb.toString();
	}
	

	
}
