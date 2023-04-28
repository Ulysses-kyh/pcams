package com.suda.pcams.common.handler;

import org.springframework.stereotype.Service;
import xyz.erupt.core.annotation.EruptHandlerNaming;
import xyz.erupt.job.handler.EruptJobHandler;
import xyz.erupt.jpa.dao.EruptDao;

import javax.annotation.Resource;

/**
 * 定时设置过去的未预约的安排为过期状态
 *
 * @author Ulysses
 * @since 2023-04-23
 */
@Service
@EruptHandlerNaming("定时处理过期的安排")
public class StatusJobHandler implements EruptJobHandler {

    @Resource
    private EruptDao eruptDao;

    @Override
    public String exec(String code, String param) {
        String sqlStatus = "update pcams_setting set status = 2 where Date(date) < Date(Now())";
        eruptDao.getJdbcTemplate().execute(sqlStatus);
        return null;
    }
}
