package com.employment.mapper;

import com.employment.entity.Announcement;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AnnouncementMapper {
    
    @Select("SELECT * FROM announcement WHERE id = #{id}")
    Announcement findById(Long id);
    
    @Select("SELECT * FROM announcement ORDER BY create_time DESC")
    List<Announcement> findAll();
    
    @Select("SELECT * FROM announcement WHERE status = 1 ORDER BY create_time DESC")
    List<Announcement> findAllActive();
    
    @Insert("INSERT INTO announcement(title, content, admin_id, status) " +
            "VALUES(#{title}, #{content}, #{adminId}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Announcement announcement);
    
    @Update("UPDATE announcement SET title=#{title}, content=#{content}, status=#{status} WHERE id=#{id}")
    int update(Announcement announcement);
    
    @Update("UPDATE announcement SET status=#{status} WHERE id=#{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    @Delete("DELETE FROM announcement WHERE id = #{id}")
    int delete(Long id);
}
