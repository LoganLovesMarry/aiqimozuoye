package com.employment.controller;

import com.employment.common.Result;
import com.employment.entity.Announcement;
import com.employment.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    
    @Autowired
    private AnnouncementService announcementService;
    
    @GetMapping("/list")
    public Result<List<Announcement>> getAll() {
        List<Announcement> announcements = announcementService.findAll();
        return Result.success(announcements);
    }
    
    @GetMapping("/active")
    public Result<List<Announcement>> getActive() {
        List<Announcement> announcements = announcementService.findAllActive();
        return Result.success(announcements);
    }
    
    @GetMapping("/{id}")
    public Result<Announcement> getById(@PathVariable Long id) {
        Announcement announcement = announcementService.findById(id);
        if (announcement != null) {
            return Result.success(announcement);
        }
        return Result.error("公告不存在");
    }
    
    @PostMapping
    public Result<Announcement> create(@RequestBody Announcement announcement) {
        try {
            Announcement created = announcementService.create(announcement);
            return Result.success(created);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Announcement announcement) {
        try {
            announcement.setId(id);
            announcementService.update(announcement);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/hide")
    public Result<?> hide(@PathVariable Long id) {
        try {
            announcementService.hide(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/show")
    public Result<?> show(@PathVariable Long id) {
        try {
            announcementService.show(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        try {
            announcementService.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
