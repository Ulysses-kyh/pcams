package com.suda.pcams.model;

import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Filter;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * 心理老师时间安排管理
 *
 * @author Ulysses
 * @since 2023-04-23
 */
@Getter
@Setter
@Entity
@Erupt(name = "时间安排管理",
        filter = @Filter("PcamsSetting.status = 0 and Date(PcamsSetting.date) > Date(Now())"
        )
)
@Table(name = "pcams_setting")
public class PcamsSetting extends BaseModel {

    // 心理老师
    @ManyToOne //多对一
    @EruptField(
            views = {
                    @View(title = "心理老师姓名", column = "teacherName"),
                    @View(title = "心理老师手机号", column = "teacherPhone"),
                    @View(title = "心理老师邮箱", column = "teacherMail"),
                    @View(title = "咨询室校区", column = "pcamsRoom.campus"),
                    @View(title = "咨询室教学楼", column = "pcamsRoom.building"),
                    @View(title = "咨询室教室号", column = "pcamsRoom.roomId"),
                    @View(title = "心理咨询室", column = "pcamsRoom.roomDescription"),
            },
            edit = @Edit(title = "心理老师", notNull = true, type = EditType.REFERENCE_TABLE,
                    referenceTableType = @ReferenceTableType(id = "id", label = "teacherName"))
    )
    private PcamsTeacher pcamsTeacher;

    // 预约日期
    @EruptField(
            views = @View(title = "预约日期", sortable = true),
            edit = @Edit(title = "预约日期",
                    search = @Search,
                    notNull = true,
                    type = EditType.DATE,
                    dateType = @DateType(type = DateType.Type.DATE, pickerMode = DateType.PickerMode.FUTURE))
    )
    private String date = LocalDate.now().plusDays(1).toString();

    // 预约开始时间
    @EruptField(
            views = @View(title = "预约开始时间"),
            edit = @Edit(title = "预约开始时间", type = EditType.DATE, dateType = @DateType(type = DateType.Type.TIME))
    )
    private String startTime = "08:30:00";

    // 预约结束时间
    @EruptField(
            views = @View(title = "预约结束时间"),
            edit = @Edit(title = "预约结束时间", type = EditType.DATE, dateType = @DateType(type = DateType.Type.TIME))
    )
    private String endTime = "11:30:00";

    // 预约状态 0-未预约 1-已预约 2-已过期
    @EruptField(
            views = @View(title = "预约状态", sortable = true),
            edit = @Edit(title = "预约状态",
                    show = false,
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            vl = {
                                    @VL(label = "未预约", value = "0"),
                                    @VL(label = "已预约", value = "1"),
                                    @VL(label = "已过期", value = "2"),
                            }
                    ))
    )
    private int status = 0;
}