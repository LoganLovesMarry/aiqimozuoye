package com.employment.mapper;

import com.employment.entity.Application;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ApplicationMapper {
    
    @Select("SELECT a.*, s.name as student_name, j.title as job_title, c.name as company_name " +
            "FROM application a " +
            "LEFT JOIN student s ON a.student_id = s.id " +
            "LEFT JOIN job j ON a.job_id = j.id " +
            "LEFT JOIN company c ON j.company_id = c.id " +
            "WHERE a.id = #{id}")
    Application findById(Long id);
    
    @Select("SELECT a.*, s.name as student_name, j.title as job_title, c.name as company_name " +
            "FROM application a " +
            "LEFT JOIN student s ON a.student_id = s.id " +
            "LEFT JOIN job j ON a.job_id = j.id " +
            "LEFT JOIN company c ON j.company_id = c.id " +
            "WHERE a.student_id = #{studentId}")
    List<Application> findByStudentId(Long studentId);
    
    @Select("SELECT a.*, s.name as student_name, j.title as job_title, c.name as company_name " +
            "FROM application a " +
            "LEFT JOIN student s ON a.student_id = s.id " +
            "LEFT JOIN job j ON a.job_id = j.id " +
            "LEFT JOIN company c ON j.company_id = c.id " +
            "WHERE j.company_id = #{companyId}")
    List<Application> findByCompanyId(Long companyId);
    
    @Select("SELECT * FROM application WHERE student_id = #{studentId} AND job_id = #{jobId}")
    Application findByStudentAndJob(@Param("studentId") Long studentId, @Param("jobId") Long jobId);
    
    @Insert("INSERT INTO application(student_id, job_id, status) " +
            "VALUES(#{studentId}, #{jobId}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Application application);
    
    @Update("UPDATE application SET status=#{status} WHERE id=#{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    @Delete("DELETE FROM application WHERE id = #{id}")
    int delete(Long id);
}
