package com.java.user;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.java.user.RoleDTO;
import com.java.user.UserDTO;
import com.java.user.UserRole;

@Mapper
public interface UserMapper {
	
	@Select("		SELECT a.userNo, a.`name`, d.`name` AS deptName, b.`name` AS roleName "
			+ 	"	FROM `stg_user` AS a                                                  "
			+ 	"	LEFT OUTER JOIN                                                       "
			+ 	"	(                                                                     "
			+ 	"		SELECT ur.`userNo`, r.`name`                                      "
			+ 	"	   FROM `stg_role` AS r                                               "
			+ 	"		INNER JOIN `stg_user_role` AS ur                                  "
			+ 	"		ON (r.`roleNo` = ur.`roleNo`)                                     "
			+ 	"	) AS b                                                                "
			+ 	"	ON (a.`userNo` = b.`userNo`)                                          "
			+ 	"	INNER JOIN                                                            "
			+ 	"		`stg_dept` AS d                                                   "
			+ 	"		ON (a.`deptNo` = d.`deptNo`)                                      "
			+ 	"	WHERE a.`useYN` = 'Y' AND a.`userNo` <> 1  -- 관리자는 표시제외            ")
	public List<UserDTO> findALL();

	@Select("SELECT * FROM `stg_user` WHERE `useYN` = 'Y' AND email = #{email}")
	public UserDTO findByUser(String email);
	
	@Select("SELECT * FROM `stg_role` WHERE `roleNo` = (SELECT `roleNo` FROM `stg_user_role` AS a WHERE a.`userNo` = #{no})")
	public RoleDTO findByRole(int no);
	
	@SelectKey(statementType = StatementType.PREPARED, statement = "select last_insert_id() as no", keyProperty = "userNo", before = false, resultType = int.class)
	@Insert("INSERT INTO `stg_user` (`name`, `deptNo`, `pwd`, `email`, `phone`, `zipcode`, `adr`, `detail_adr`) VALUE (#{name}, #{deptNo}, #{pwd}, #{email}, #{phone}, #{zipcode}, #{adr}, #{detail_adr})")
	public int save(UserDTO user);
	
	@Insert("INSERT INTO `stg_user_role` VALUE (#{roleNo}, #{userNo})")
	public int saveUserRole(UserRole userRole);
	
	@Update("UPDATE stg_user SET `useYN` = 'N' WHERE userNo = #{UserNo}")
	public int delete(String UserNo);
	
}
