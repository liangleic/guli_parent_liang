package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-10-20
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    // 1、查询讲师表所有数据
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        return teacherService.list(null);
    }

    // 2、逻辑删除讲师的方法
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public boolean removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return flag;
    }

}

