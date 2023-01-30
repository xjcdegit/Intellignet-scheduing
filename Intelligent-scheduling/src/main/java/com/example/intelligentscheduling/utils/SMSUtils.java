package com.example.intelligentscheduling.utils;

import com.alibaba.fastjson.JSONException;
import com.example.intelligentscheduling.entity.User;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 短信发送工具类
 */
@Slf4j
public class SMSUtils {

	@RequestMapping("testTenccent" )
	@ResponseBody
	public static void sendMessage(User user, HttpSession session, String phone){
		// 短信应用 SDK AppID
		int appid = 1400746885; // SDK AppID 以1400开头
		// 短信应用 SDK AppKey
		String appkey = "6c83ed56aebf7f1f4ec2834a4d1f0489";
		// 需要发送短信的手机号
		String phoneNumbers = user.getPhone();

		// 短信模板 ID，需要在短信应用中申请
		int templateId = 1564555; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
// 签名
		String smsSign = "xjcedu个人公众号"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请

		try {
			//生成随机的四位验证码
//			String[] params = {code};
			String[] params = {ValidateCodeUtils.generateValidateCode(6).toString()};
				session.setAttribute(phone,params[0]);
				log.info("验证码 ： " +  params[0]);
			//将生成的验证码保存到Session中


			SmsSingleSender sender = new SmsSingleSender(appid, appkey);
			SmsSingleSenderResult result = sender.sendWithParam("86", phoneNumbers,
					templateId, params, smsSign, "", "");
			System.out.println(result);
		} catch (HTTPException e) {
			// HTTP 响应码错误
			e.printStackTrace();
		} catch (JSONException e) {
			// JSON 解析错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络 IO 错误
			e.printStackTrace();
		}
	}

}
