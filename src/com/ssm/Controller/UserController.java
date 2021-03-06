package com.ssm.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.ssm.bean.songlist;
import com.ssm.bean.user;
import com.ssm.mail.test;
import com.ssm.service.SonglistService;
import com.ssm.service.UserService;

import net.sf.json.JSONObject;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(value="/user",produces={"application/json;charset=utf-8"})
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private SonglistService songlistService;
	@RequestMapping("login")
	public @ResponseBody JSONObject login(user u,HttpServletResponse res,HttpServletRequest req){
//		Cookie[] cookies=req.getCookies();
//		for(Cookie c:cookies){
//			System.out.println(c.getName());
//		}
//		System.out.println(u.getUsername()+";"+u.getUserpassword());
		user us=userService.login(u);
		JSONObject jo = new JSONObject();
		if(us!=null){
//			System.out.println("???????????????");
			//??????????????????cookie??????????????????
			
			jo.put("ret","???????????????");
			jo.put("id",us.getUserid());
			return jo;
		}else{
//			System.out.println("????????????????????????");
			jo.put("ret","????????????????????????");
			return jo;
		}
	}
	
	@RequestMapping("selectall")
	public @ResponseBody List<user> selectAll(){
		
		List<user> list=userService.selectAll();
		for(user u:list){
			System.out.println(u.getUsername());
	
		}
		return list;
	}
	@RequestMapping("register")
	public @ResponseBody String register(user u){
		HttpServletRequest res = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession Session = res.getSession();//??????Session??????
		//???????????????
		//???double???????????????????????????
//		String vftest=ra+"";//???????????????
		//????????????????????????
		String vf=res.getParameter("vfcode");//????????????????????????
		Object vfcode=res.getServletContext().getAttribute("rand");
		String vfc=vfcode+"";
//		Integer yz=Integer.parseInt(vf);
//		Integer yz1=Integer.parseInt(vfc);
//		System.out.println(vf.getClass().getName()+";"+vfc.getClass().getName());
//		System.out.println(vf+";"+vfc);
//		System.out.println(email);
		if(vf.equals(vfc)){
			Date date=new Date(); 
			u.setCtime(date);
			userService.register(u);
			user us=userService.login(u);
			songlist so=new songlist();
			so.setUserid(us.getUserid());
			so.setSlistname("??????????????????");
			so.setSjurisdiction(0);
			so.setSctime(date);
			songlistService.add(so);
			//???????????????????????????????????????????????????????????????
			File file=new File("F:\\myproject\\"+us.getUsername()+"\\headportrait");
			file.mkdirs();
			return "????????????!";
		}else{
			return "???????????????!";
		}
		
	}
	//?????????????????????
	@RequestMapping("yz")
	public @ResponseBody String yz(){
		//??????????????????????????????
		int max=99999;
		int min=10000;
		int ra=(new Double(Math.floor(Math.random()*(max-min)+min))).intValue();
		HttpServletRequest res = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String email=res.getParameter("email");
		HttpSession session = res.getSession();
		session.setAttribute("email", email);//???????????????session???
		session.setAttribute("rand",ra);
		test.myemail();//????????????????????????
		return "false";
	}
	@RequestMapping("uplode")
	public @ResponseBody String uplode(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest res) throws IllegalStateException, IOException{
		String use=res.getParameter("userid");//???????????????????????????
		//?????????????????????int??????
		Integer userid=Integer.parseInt(use);
		//????????????????????????????????????????????????
		String str=new String(file.getOriginalFilename().getBytes("iso-8859-1"),"utf-8");
		//??????file.Transto ????????????????????????
		long startTime=System.currentTimeMillis();//????????????
//		System.out.println("?????????:"+str);//???????????????
		//????????????????????????????????????????????????????????????
		int last=file.getOriginalFilename().lastIndexOf(".");//????????????.?????????
		String su=file.getOriginalFilename().substring(last+1).toLowerCase();
//		System.out.println(file.getOriginalFilename().substring(last+1));
		//??????id???????????????
		user us=userService.selectByPrimaryKey(userid);
		String username=us.getUsername();
		String path="";
		if(su.equalsIgnoreCase("mp3")){
//			System.out.println("????????????????????????");
			path="F:/myproject/usersong/"+new Date().getTime()+str;
			
		}else{
			path="F:/myproject/"+username+"/headportrait/"+new Date().getTime()+str;
			//??????????????????????????????
			user u=new user();
			u.setImg(path);
			u.setUserid(userid);
			userService.updatebyid(u);
//			System.out.println("??????????????????");
		
		}

		//??????????????????
//		String path="F:/myproject/userimg/"+new Date().getTime()+file.getOriginalFilename();
		//new???File
		File newFile=new File(path);
		//??????CommonsMultipartFile????????????????????????
		file.transferTo(newFile);//??????????????????
		long endTime=System.currentTimeMillis();//????????????
		//?????????????????????????????????
		System.out.println("??????file.Transto??????????????????"+String.valueOf(endTime-startTime)+"ms");
		return "/succes";
	}
	@RequestMapping("/download")
	public ResponseEntity<byte[]> export(String fileName,String filePath) throws IOException {  
    	
        HttpHeaders headers = new HttpHeaders();    
        File file = new File(filePath);
        
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
        headers.setContentDispositionFormData("attachment", fileName);    
       
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                              headers, HttpStatus.CREATED);    
    } 
	@RequestMapping("updatebyid")
	public @ResponseBody Boolean updatebyid(user u){
		
		Boolean flag=userService.updatebyid(u);
		return flag;
	}
	String filePath = "";
	@RequestMapping("selectByPrimaryKey")
	public @ResponseBody Map selectByPrimaryKey(Integer userid){
		Map usermap=new HashMap();
		String sex="";
		user u=userService.selectByPrimaryKey(userid);
		if(u.getSex().equals("")){
			sex="??????";
		}else{
			sex=u.getSex();
		}
		filePath=u.getImg();
		usermap.put("username",u.getUsername());
		usermap.put("presentation",u.getPresentation());
		usermap.put("sex",sex);
		usermap.put("age",u.getAge());
		usermap.put("site",u.getSite());
		usermap.put("img",u.getImg());
		return usermap;
	}
	//??????????????????????????????????????????????????????
	@RequestMapping("selectbyusername")
	public @ResponseBody Boolean selectbyusername(String username){
		Boolean flag=null;
		user u=userService.selectbyusername(username);
		if(u==null){
			//?????????????????????????????????
			flag=true;
		}else{
			//??????????????????????????????????????????
			flag=false;
		}
		return flag;
	}
	
	
	@RequestMapping("/getImage")
    public @ResponseBody void getImagesId(HttpServletResponse rp) {
        File imageFile = new File(filePath);
        if (imageFile.exists()) {
            FileInputStream fis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(imageFile);
                os = rp.getOutputStream();
                int count = 0;
                byte[] buffer = new byte[1024 * 8];
                while ((count = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, count);
                    os.flush();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
	//??????cookie
	public static void addCookie(String username,String userpassword,HttpServletRequest req,HttpServletResponse res){
		//??????cookie
		Cookie newcookie=new Cookie(username, userpassword);
		//??????cookie????????????
		newcookie.setMaxAge(7*24*60*60);
		//???cookie???????????????
		res.addCookie(newcookie);
	}
	@RequestMapping("/getCookie")
	public @ResponseBody Map<String,String> initCookie(String username,HttpServletRequest request){
		Cookie[] cookie = request.getCookies();
		Map<String,String> map = new HashMap();
		for(Cookie c : cookie){
			if(c.getName().equals(username)){
				String password=c.getValue();
				map.put("username", username);
				map.put("password",password);
				return map;
				}
			}
		return null;
	}
}
