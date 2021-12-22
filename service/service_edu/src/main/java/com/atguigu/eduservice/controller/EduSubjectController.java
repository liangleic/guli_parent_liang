package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    //获取上传过来的文件，把文件内容读取出来
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //上传过来excel文件
        eduSubjectService.saveSubject(file, eduSubjectService);
        return R.ok();
    }

    /**
     * 课程分类的列表（树型）,返回树型格式的数据
     */
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        //list的泛型是一级分类
        List<OneSubject> list = eduSubjectService.getAllOneSubject();
        return R.ok().data("list", list);
    }


}

