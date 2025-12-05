package com.employment.mapper;

import com.employment.entity.Admin;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AdminMapper {
    
    @Select("SELECT * FROM admin WHERE id = #{id}")
    Admin findById(Long id);
    
    @Select("SELECT * FROM admin WHERE username = #{username}")
    Admin findByUsername(String username);
    
    @Select("SELECT * FROM admin")
    List<Admin> findAll();
    
    @Insert("INSERT INTO admin(username, password, role) " +
            "VALUES(#{username}, #{password}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Admin admin);
    
    @Update("UPDATE admin SET password=#{password} WHERE id=#{id}")
    int updatePassword(@Param("id") Long id, @Param("password") String password);
    
    @Delete("DELETE FROM admin WHERE id = #{id}")
    int delete(Long id);
}
