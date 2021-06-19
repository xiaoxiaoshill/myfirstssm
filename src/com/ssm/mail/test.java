package com.ssm.mail;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class test {
    public static void myemail(){
    	HttpServletRequest hre = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	HttpSession session = hre.getSession();
    	String email=(String) session.getAttribute("email");//从session中取
    	int ra= (int) session.getAttribute("rand");//一种会话，会话结束，数据销毁
    	hre.getServletContext().setAttribute("rand", ra);//存入application
//    	System.out.println(email);
        MailOperation operation = new MailOperation();
        String user = "liulun_1673_yt@163.com";
        String password = "RROIZNXCUYDWDKEC";
        String host = "smtp.163.com";
        String from = "liulun_1673_yt@163.com";
        String to = email;// 收件人
        String subject = "网云许可";
        //邮箱内容
        StringBuffer sb = new StringBuffer();
        
        sb.append("<h1>"+"数字:"+ra+"</h1>");
        try {
            String res = operation.sendMail(user, password, host, from, to,
                    subject, sb.toString());
            System.out.println(res);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
