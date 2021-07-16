package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.SetmealDao;
import com.atguigu.service.ReportService;
import com.atguigu.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    MemberDao memberDao;

    @Autowired
    OrderDao orderDao;

    /**
     * 获得运营统计数据
     * Map数据格式：
     *      reportDate（当前时间）--String
     *      todayNewMember（今日新增会员数） -> number
     *      totalMember（总会员数） -> number
     *      thisWeekNewMember（本周新增会员数） -> number
     *      thisMonthNewMember（本月新增会员数） -> number
     *
     *      todayOrderNumber（今日预约数） -> number
     *      todayVisitsNumber（今日出游数） -> number
     *      thisWeekOrderNumber（本周预约数） -> number
     *      thisWeekVisitsNumber（本周出游数） -> number
     *      thisMonthOrderNumber（本月预约数） -> number
     *      thisMonthVisitsNumber（本月出游数） -> number
     *
     *      hotSetmeal（热门套餐（取前4）） -> List<Map>
     */

    @Override
    public Map getBusinessReportData() {
        Map map = null;

        try {
            // 日期工具类
            // 1：当前时间
            String today = DateUtils.parseDate2String(DateUtils.getToday());
            // 2：本周（周一）
            String weekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
            // 3：本周（周日）
            String weekSunday = DateUtils.parseDate2String(DateUtils.getSundayOfThisWeek());
            // 4：本月（1号）
            String monthFirst = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
            // 5：本月（31号）
            String monthLast = DateUtils.parseDate2String(DateUtils.getLastDay4ThisMonth());

            // （1）今日新增会员数
            int todayNewMember = memberDao.getTodayNewMember(today);
            // （2）总会员数
            int totalMember = memberDao.getTotalMember();
            // （3）本周新增会员数
            int thisWeekNewMember = memberDao.getThisWeekAndMonthNewMember(weekMonday);
            // （4）本月新增会员数
            int thisMonthNewMember = memberDao.getThisWeekAndMonthNewMember(monthFirst);

            // （5）今日预约数
            int todayOrderNumber = orderDao.getTodayOrderNumber(today);
            // （6）今日出游数
            int todayVisitsNumber = orderDao.getTodayVisitsNumber(today);
            // （7）本周预约数
            Map<String,Object> paramWeek = new HashMap<String,Object>();
            paramWeek.put("begin",weekMonday);
            paramWeek.put("end",weekSunday);
            int thisWeekOrderNumber = orderDao.getThisWeekAndMonthOrderNumber(paramWeek);
            // （9）本月预约数
            Map<String,Object> paramMonth = new HashMap<String,Object>();
            paramMonth.put("begin",monthFirst);
            paramMonth.put("end",monthLast);
            int thisMonthOrderNumber = orderDao.getThisWeekAndMonthOrderNumber(paramMonth);
            // （8）本周出游数
            Map<String,Object> paramWeekVisit = new HashMap<String,Object>();
            paramWeekVisit.put("begin",weekMonday);
            paramWeekVisit.put("end",weekSunday);
            int thisWeekVisitsNumber = orderDao.getThisWeekAndMonthVisitsNumber(paramWeekVisit);
            // （10）本月出游数
            Map<String,Object> paramMonthVisit = new HashMap<String,Object>();
            paramMonthVisit.put("begin",monthFirst);
            paramMonthVisit.put("end",monthLast);
            int thisMonthVisitsNumber = orderDao.getThisWeekAndMonthVisitsNumber(paramMonthVisit);

            // （11）热门套餐
            List<Map<String,Object>> hotSetmeal = orderDao.findHotSetmeal();


            //封装数据结果
            map = new HashMap();

            map.put("reportDate",today);

            map.put("todayNewMember",todayNewMember);
            map.put("totalMember",totalMember);
            map.put("thisWeekNewMember",thisWeekNewMember);
            map.put("thisMonthNewMember",thisMonthNewMember);

            map.put("todayOrderNumber",todayOrderNumber);
            map.put("todayVisitsNumber",todayVisitsNumber);
            map.put("thisWeekOrderNumber",thisWeekOrderNumber);
            map.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
            map.put("thisMonthOrderNumber",thisMonthOrderNumber);
            map.put("thisMonthVisitsNumber",thisMonthVisitsNumber);

            map.put("hotSetmeal",hotSetmeal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
