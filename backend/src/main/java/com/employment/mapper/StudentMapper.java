package com.employment.mapper;

import com.employment.entity.Student;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface StudentMapper {
    
    @Select("SELECT * FROM student WHERE id = #{id}")
    Student findById(Long id);
    
    @Select("SELECT * FROM student WHERE email = #{email}")
    Student findByEmail(String email);
    
    @Select("SELECT * FROM student")
    List<Student> findAll();
    
    @Insert("INSERT INTO student(name, email, password, major, status) " +
            "VALUES(#{name}, #{email}, #{password}, #{major}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Student student);
    
    @Update("UPDATE student SET name=#{name}, major=#{major}, resume_url=#{resumeUrl}, " +
            "status=#{status} WHERE id=#{id}")
    int update(Student student);
    
    @Update("UPDATE student SET password=#{password} WHERE id=#{id}")
    int updatePassword(@Param("id") Long id, @Param("password") String password);
    
    @Delete("DELETE FROM student WHERE id = #{id}")
    int delete(Long id);
    
    @Select("SELECT COUNT(*) FROM student WHERE status = 1")
    int countEmployed();
    
    @Select("SELECT COUNT(*) FROM student")
    int countAll();
}
