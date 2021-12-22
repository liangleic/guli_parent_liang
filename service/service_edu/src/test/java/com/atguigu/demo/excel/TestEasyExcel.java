package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TestEasyExcel$
 * @Author liang
 * @Date: 2021/12/11$ 17:20$
 * @Version 1.0
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        //1、设置写入文件夹地址和excel文件名称
        String fileName = "F:\\easyexceltest.xlsx";

        //2、调用easyexcel里面的方法实现写操作
//        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(data());



        //实现excel的读操作
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list;
    }
}
