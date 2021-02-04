package gg.hta.lol.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

//import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import gg.hta.lol.dao.MemberDao;
import gg.hta.lol.vo.AuthVo;

@Service
public class MailSendService {
//	@Autowired private JavaMailSender mailSender;
	private JavaMailSender mailSender;
//	@Autowired private SqlSessionTemplate sqlSession;
	@Autowired private MemberDao dao;
	
	// 이메일 난수 만드는 메서드
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;

		do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}
		} while (sb.length() < size);
		
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}

	// 난수를 이용한 키 생성
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}

	// 회원가입 발송 이메일(인증키 발송)
	public void mailSendWithUserKey(String email, String id, HttpServletRequest request) {
		String key = getKey(false, 20);
		System.out.println("==========================================");
		System.out.println(key);
//		userDao = sqlSession.getMapper(UserDaoInterface.class);
		AuthVo vo = new AuthVo(id, key);
		dao.insert(vo);
//		String code = Integer.toString(SHA256.getSHA256(email).hashCode());
//		AuthVo vo = new AuthVo(id, code);
//		dao.insert(vo);
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h1>홈페이지 가입을 해주셔서 감사합니다.</h1><br>" +
                		 "인증 번호는 " + key + "입니다.<br>" + 
                		 "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		try {
			mail.setSubject("[본인인증]"+ id + "님의 인증메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}	
	}
}