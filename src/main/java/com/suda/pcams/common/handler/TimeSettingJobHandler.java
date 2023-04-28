package com.suda.pcams.common.handler;

import com.suda.pcams.model.PcamsTeacher;
import org.springframework.stereotype.Service;
import xyz.erupt.core.annotation.EruptHandlerNaming;
import xyz.erupt.job.handler.EruptJobHandler;
import xyz.erupt.jpa.dao.EruptDao;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * 新增未来五天的时间安排
 *
 * @author Ulysses
 * @since 2023-04-23
 */
@Service
@EruptHandlerNaming("定时新增未来时间安排")
public class TimeSettingJobHandler implements EruptJobHandler {

    @Resource
    private EruptDao eruptDao;

    @Override
    public String exec(String code, String param) {
        // 获取所有的心理老师
        List<PcamsTeacher> teachers = eruptDao.queryEntityList(PcamsTeacher.class);
        for (PcamsTeacher teacher : teachers) {
            long id = teacher.getId();
            for (int i = 1; i < 6; i++) {
                String date = LocalDate.now().plusDays(i).toString();
                String sqlDate = "insert into pcams_setting (`pcams_teacher_id`,`date`,`start_time`,`end_time`,`status`) values ( '" +
                        id + "','" + date + "', '08:30:00','11:30:00','0')";
//                System.out.println(sqlDate);
                eruptDao.getJdbcTemplate().execute(sqlDate);
            }
        }
        return null;
    }
}
