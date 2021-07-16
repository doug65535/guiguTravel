package com.atguigu.quartz;

import java.util.Date;

//@Component  声明bean对象
public class JobDemo {
    public void run(){
        System.out.println("new Date() = " + new Date());
    }
}
