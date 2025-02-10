package com.java.user;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.java.user.MyUserDTO;
import com.java.user.RoleDTO;
import com.java.user.UserDTO;
import com.java.user.UserRole;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService, UserDetailsService {

	private final UserDao userDAO;
	
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserDTO user = userDAO.findByUser(email);
		if(user != null) {
			RoleDTO role = userDAO.findByRole(user.getUserNo());
			
			if(role != null) {
				return new MyUserDTO(user, role);
			}
		}
		
		return null;
	}
	
	@Override
	public String list(Model model, HttpServletRequest req) {		
		model.addAttribute("rs", userDAO.findALL());
		return "user/list";
	}
	
	public String save(UserDTO user) {
		user.setPwd(passwordEncoder.encode(user.getPwd()));
		user.setDeptNo(2); //기본 일반 유저로 등록		
		
		int status = userDAO.save(user);
		if(status == 1) {			
			status = userDAO.saveUserRole(UserRole.builder().userNo(user.getUserNo()).roleNo(2).build());
			if(status == 1) {
				
				// 로그인 여부 확인
	            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	            if (authentication != null && authentication.isAuthenticated()) {	// 사용자 인증 상태 확인                
	                return "redirect:/user/list"; // 인증된 사용자는 /user/list로 리디렉션
	            } else {	                
	                return "redirect:/signIn"; // 인증되지 않은 사용자는 /signIn으로 리디렉션
	            }
			}
		}
		return "redirect:/signUp";
	}
	
	// 이메일 중복 체크
	public UserDTO email_du_chk(String email) {		
		return userDAO.findByUser(email);		
	}
	
	// 직원삭제
	@Override
	public boolean delete(String userNo) {
		try {			
			int status = userDAO.delete(userNo);
			if(status == 1) return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
