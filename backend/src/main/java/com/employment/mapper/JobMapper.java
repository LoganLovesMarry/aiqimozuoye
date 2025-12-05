package com.employment.mapper;

import com.employment.entity.Job;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface JobMapper {
    
    @Select("SELECT j.*, c.name as company_name FROM job j " +
            "LEFT JOIN company c ON j.company_id = c.id WHERE j.id = #{id}")
    Job findById(Long id);
    
    @Select("SELECT j.*, c.name as company_name FROM job j " +
            "LEFT JOIN company c ON j.company_id = c.id WHERE j.status = 1")
    List<Job> findAllActive();
    
    @Select("SELECT j.*, c.name as company_name FROM job j " +
            "LEFT JOIN company c ON j.company_id = c.id WHERE j.company_id = #{companyId}")
    List<Job> findByCompanyId(Long companyId);
    
    @Select("<script>" +
            "SELECT j.*, c.name as company_name FROM job j " +
            "LEFT JOIN company c ON j.company_id = c.id WHERE 1=1 " +
            "<if test='title != null'> AND j.title LIKE CONCAT('%', #{title}, '%') </if>" +
            "<if test='location != null'> AND j.location = #{location} </if>" +
            "<if test='status != null'> AND j.status = #{status} </if>" +
            "</script>")
    List<Job> search(@Param("title") String title, 
                     @Param("location") String location, 
                     @Param("status") Integer status);
    
    @Insert("INSERT INTO job(title, description, salary, location, company_id, status) " +
            "VALUES(#{title}, #{description}, #{salary}, #{location}, #{companyId}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Job job);
    
    @Update("UPDATE job SET title=#{title}, description=#{description}, " +
            "salary=#{salary}, location=#{location}, status=#{status} WHERE id=#{id}")
    int update(Job job);
    
    @Update("UPDATE job SET status=#{status} WHERE id=#{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    @Delete("DELETE FROM job WHERE id = #{id}")
    int delete(Long id);
}
