package com.nt.runners;

import java.util.Arrays;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
@Component
public class RunClass implements CommandLineRunner {

	@Autowired
	JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	String fromMail;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		String[] toMails= {"17r91a0416tkrec@gmail.com", "venkat270499@gmail.com","annadikrishna11@gmail.com"};
		String[] items= {"Apples", "chairs", "Biscuits"};
		int[] prices= {120,900,100};
		String s=purchase(toMails,prices,items);
		System.out.println(s);
	}
	
	public String purchase(String[] toMails, int[] price, String[] items) throws Exception {
		int tot=0;
		for(int p:price)
			tot+=p;
		String msg="Price for all the items "+Arrays.toString(items)+" is "+tot+ " and you OTP is 5789";
		sendMsg(msg,toMails);
		return msg;
	}
	public void sendMsg(String msg, String[] toMails) throws Exception{
		MimeMessage message=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		
		helper.setSubject("This message is to Krishna Kindly note the OTP.");
		helper.setCc(toMails);
		helper.setText(msg);
		helper.setSentDate(new Date());
		sender.send(message);
	}

}
