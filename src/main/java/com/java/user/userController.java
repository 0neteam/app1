package com.java.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class userController {

	@GetMapping("/user/create")
	public String userCreate() {
		
		return "/user/create";
	}
	
	@GetMapping("/user/detail")
	public String userDetail() {
		
		return "/user/detail";
	}
	
	@GetMapping("/user/edit")
	public String userEdit() {
		
		return "/user/edit";
	}
	
	@GetMapping("/user/list")
	public String userList() {
		
		return "/user/list";
	}
}
