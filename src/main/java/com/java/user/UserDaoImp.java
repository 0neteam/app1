package com.java.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.user.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserDaoImp implements UserDao {

	final UserMapper userMapper;
	
	@Override
	public List<UserDTO> findALL() {
		// TODO Auto-generated method stub
		return userMapper.findALL();
	}

	@Override
	public UserDTO findByUser(String email) {
		// TODO Auto-generated method stub
		return userMapper.findByUser(email);
	}

	@Override
	public int save(UserDTO user) {
		// TODO Auto-generated method stub
		return userMapper.save(user);
	}

	@Override
	public int saveUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return userMapper.saveUserRole(userRole);
	}

	@Override
	public RoleDTO findByRole(int no) {
		// TODO Auto-generated method stub
		return userMapper.findByRole(no);
	}

	@Override
	public int delete(String UserNo) {
		// TODO Auto-generated method stub
		return userMapper.delete(UserNo);
	}

}
