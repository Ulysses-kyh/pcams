package com.suda.pcams.model;

import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTableType;
import xyz.erupt.annotation.sub_field.sub_edit.VL;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 心理老师管理
 *
 * @author Ulysses
 * @since 2023-04-23
 */
@Getter
@Setter
@Entity
@Erupt(name = "心理老师管理")
@Table(name = "pcams_teacher")
public class PcamsTeacher extends BaseModel {

    // 心理老师工号,用于登陆
    @EruptField(
            views = @View(title = "心理老师工号", show = false),
            edit = @Edit(title = "心理老师工号", notNull = true)
    )
    private String teacherId;

    // 心理老师姓名
    @EruptField(
            views = @View(title = "心理老师姓名"),
            edit = @Edit(title = "心理老师姓名", notNull = true)
    )
    private String teacherName;

    // 心理老师性别
    @EruptField(
            views = @View(title = "心理老师性别"),
            edit = @Edit(title = "心理老师性别", type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            vl = {
                                    @VL(label = "男", value = "A"),
                                    @VL(label = "女", value = "B"),
                            }
                    ))
    )
    private String teacherSex;

    // 心理老师邮箱
    @EruptField(
            views = @View(title = "心理老师邮箱"),
            edit = @Edit(title = "心理老师邮箱", notNull = true)
    )
    private String teacherMail;

    // 心理老师手机号
    @EruptField(
            views = @View(title = "心理老师手机号"),
            edit = @Edit(title = "心理老师手机号", notNull = true)
    )
    private String teacherPhone;

    // 心理老师描述
    @EruptField(
            views = @View(title = "心理老师描述"),
            edit = @Edit(title = "心理老师描述")
    )
    private String teacherDescription;

    // 心理咨询室
    @ManyToOne
    @EruptField(
            views = {
                    @View(title = "咨询室校区", column = "campus"),
                    @View(title = "咨询室教学楼", column = "building"),
                    @View(title = "咨询室教室号", column = "roomId"),
                    @View(title = "心理咨询室", column = "roomDescription")
            },
            edit = @Edit(title = "心理咨询室", notNull = true, type = EditType.REFERENCE_TABLE,
                    referenceTableType = @ReferenceTableType(id = "id", label = "roomDescription"))
    )
    private PcamsRoom pcamsRoom;
}