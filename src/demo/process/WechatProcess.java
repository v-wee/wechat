package demo.process;

import java.util.Date;

import demo.entity.ReceiveXmlEntity;

/**
 * 微信xml消息处理流程逻辑类
 *
 */
public class WechatProcess {
	/**
	 * 解析处理xml、获取智能回复结果（通过图灵机器人api接口）
	 * @param xml 接收到的微信数据
	 * @return	最终的解析结果（xml格式数据）
	 */
	public String processWechatMag(String xml){
		/** 解析xml数据 */
		ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);
		
		/** 以文本消息为例，调用图灵机器人api接口，获取回复内容 */
		String result = "";
		
		/** 如果是新用户订阅 */
		if("event".endsWith(xmlEntity.getMsgType()) && "subscribe".equals(xmlEntity.getEvent())){
			result = "欢迎订阅魏大宝，没事和我聊聊天，我给你讲笑话~";
		}else if("text".endsWith(xmlEntity.getMsgType())){
			result = new TulingApiProcess().getTulingResult(xmlEntity.getContent());
		}
		
		/** 此时，如果用户输入的是“你好”，在经过上面的过程之后，result为“你也好”类似的内容 
		 *  因为最终回复给微信的也是xml格式的数据，所有需要将其封装为文本类型返回消息
		 * */
		result = new FormatXmlProcess().formatTextAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
		
		return result;
	}
}
