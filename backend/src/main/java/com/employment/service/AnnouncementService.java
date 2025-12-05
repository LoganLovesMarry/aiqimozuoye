package com.employment.service;

import com.employment.entity.Announcement;
import com.employment.mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnouncementService {
    
    @Autowired
    private AnnouncementMapper announcementMapper;
    
    public Announcement findById(Long id) {
        return announcementMapper.findById(id);
    }
    
    public List<Announcement> findAll() {
        return announcementMapper.findAll();
    }
    
    public List<Announcement> findAllActive() {
        return announcementMapper.findAllActive();
    }
    
    @Transactional
    public Announcement create(Announcement announcement) {
        announcement.setStatus(1); // 默认显示
        announcementMapper.insert(announcement);
        return announcement;
    }
    
    @Transactional
    public int update(Announcement announcement) {
        return announcementMapper.update(announcement);
    }
    
    @Transactional
    public int hide(Long id) {
        return announcementMapper.updateStatus(id, 0);
    }
    
    @Transactional
    public int show(Long id) {
        return announcementMapper.updateStatus(id, 1);
    }
    
    @Transactional
    public int delete(Long id) {
        return announcementMapper.delete(id);
    }
}
