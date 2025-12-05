package com.employment.mapper;

import com.employment.entity.Company;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CompanyMapper {
    
    @Select("SELECT * FROM company WHERE id = #{id}")
    Company findById(Long id);
    
    @Select("SELECT * FROM company WHERE email = #{email}")
    Company findByEmail(String email);
    
    @Select("SELECT * FROM company")
    List<Company> findAll();
    
    @Select("SELECT * FROM company WHERE status = #{status}")
    List<Company> findByStatus(Integer status);
    
    @Insert("INSERT INTO company(name, email, password, license, status) " +
            "VALUES(#{name}, #{email}, #{password}, #{license}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Company company);
    
    @Update("UPDATE company SET name=#{name}, license=#{license}, status=#{status} WHERE id=#{id}")
    int update(Company company);
    
    @Update("UPDATE company SET status=#{status} WHERE id=#{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    @Delete("DELETE FROM company WHERE id = #{id}")
    int delete(Long id);
}
