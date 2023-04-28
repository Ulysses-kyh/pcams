package com.suda.pcams.model;

import com.suda.pcams.proxy.PcamsAppointmentProxy;
import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTableType;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 心理咨询预约管理
 *
 * @author Ulysses
 * @since 2023-04-23
 */
@Getter
@Setter
@Entity
@Erupt(name = "预约管理",
        dataProxy = PcamsAppointmentProxy.class,
        power = @Power(export = true),
        orderBy = "PcamsAppointment.pcamsSetting.date desc")
//        filter = @Filter("PcamsAppointment.status = true")
@Table(name = "pcams_appointment")
public class PcamsAppointment extends BaseModel {

    // 预约人学号/工号
    @EruptField(
            views = @View(title = "预约人学号/工号"),
            edit = @Edit(title = "预约人学号/工号", notNull = true)
    )
    private String userId;

    // 预约人姓名
    @EruptField(
            views = @View(title = "预约人姓名"),
            edit = @Edit(title = "预约人姓名", notNull = true)
    )
    private String userName;

    // 预约人手机号
    @EruptField(
            views = @View(title = "预约人手机号"),
            edit = @Edit(title = "预约人手机号", notNull = true)
    )
    private String userPhone;

    // 选择空闲时间并预约
    @ManyToOne
    @EruptField(
            views = {
                    @View(title = "预约日期", column = "date"),
                    @View(title = "预约开始时间", column = "startTime"),
                    @View(title = "预约结束时间", column = "endTime"),
                    @View(title = "心理老师姓名", column = "pcamsTeacher.teacherName"),
                    @View(title = "心理老师手机号", column = "pcamsTeacher.teacherPhone"),
                    @View(title = "心理老师邮箱", column = "pcamsTeacher.teacherMail"),
                    @View(title = "咨询室校区", column = "pcamsTeacher.pcamsRoom.campus"),
                    @View(title = "咨询室教学楼", column = "pcamsTeacher.pcamsRoom.building"),
                    @View(title = "咨询室教室号", column = "pcamsTeacher.pcamsRoom.roomId"),
                    @View(title = "心理咨询室", column = "pcamsTeacher.pcamsRoom.roomDescription"),
                    @View(title = "预约状态", column = "status"),
            },
            edit = @Edit(title = "时间安排预约表",
                    notNull = true,
                    type = EditType.REFERENCE_TABLE,
                    referenceTableType = @ReferenceTableType(id = "id", label = "date")
            )
    )
    private PcamsSetting pcamsSetting;
}