package com.java.user;

import java.util.List;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

	public String list(Model model, String searchOption, String searchKeyword);
	
	public String save(UserDTO user);
	
	public String update(UserDTO user);
	
	public UserDTO email_du_chk(String email);
	
	public boolean delete(String userNo);
	
	public String detailByUserNo(Model model, HttpServletRequest req);
	
	public String editByUserNo(Model model, HttpServletRequest req);
	
//	public int authCodeUpdate(UserDTO userDTO);
	
	public int pwdupdate(UserDTO userDTO);

	public String userDetail(Integer userNo, Model model);
	
}
