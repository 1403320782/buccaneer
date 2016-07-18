package buccaneer.system.controller;

import org.apache.shiro.session.mgt.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/b/look")
@Controller
public class TestController {

	@RequestMapping("a")
	public String test1(){
		return "test/1";
	}
	@RequestMapping("b")
	public String test2(){
		return "test/2";
	}
	@RequestMapping("c")
	public String test3(){
		return "test/3";
	}
}
