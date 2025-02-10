package com.java.user;

import java.util.List;

public interface UserDao {

	public List<UserDTO> findALL();
	
	public UserDTO findByUser(String email);
	
	public RoleDTO findByRole(int no);
	
	public int save(UserDTO user);
	
	public int saveUserRole(UserRole userRole);
	
	public int delete(String UserNo);
}
