package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-12-11
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //文件输入流
            InputStream inputStream = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {

        }

    }

    /**
     * 课程分类列表(树型)
     *
     * @return
     */
    @Override
    public List<OneSubject> getAllOneSubject() {
        //1、查询所有的一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);


        //2、查询所有的二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于封装最终的数据
        List<OneSubject> finalSubjectList = new ArrayList<>();


        //3、封装一级分类
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject subject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(subject.getId());
//            oneSubject.setTitle(subject.getTitle());
            BeanUtils.copyProperties(subject, oneSubject);


            //在一级分类循环遍历二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类
            for (int m = 0; m < twoSubjectList.size(); m++) {
                EduSubject twoSubject = twoSubjectList.get(m);
                if (twoSubject.getParentId().equals(subject.getId())){
                    TwoSubject twoSubject1 = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject,twoSubject1);
                    twoFinalSubjectList.add(twoSubject1);
                }

            }

            oneSubject.setChildren(twoFinalSubjectList);

            finalSubjectList.add(oneSubject);

        }



        //4、封闭二级分类

        return finalSubjectList;
    }
}
