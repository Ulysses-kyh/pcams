package com.suda.pcams.model;

import lombok.Getter;
import lombok.Setter;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 心理咨询室管理
 *
 * @author Ulysses
 * @since 2023-04-18
 */
@Getter
@Setter
@Entity
@Erupt(name = "心理咨询室")
@Table(name = "pcams_room")
public class PcamsRoom extends BaseModel {

    // 校区
    @EruptField(
            views = @View(title = "校区"),
            edit = @Edit(title = "校区", notNull = true)
    )
    private String campus = "天赐庄校区";

    // 教学楼
    @EruptField(
            views = @View(title = "教学楼"),
            edit = @Edit(title = "教学楼", notNull = true)
    )
    private String building = "理工楼";

    // 教室号
    @EruptField(
            views = @View(title = "教室号"),
            edit = @Edit(title = "教室号", notNull = true)
    )
    private int roomId;

    // 描述
    @EruptField(
            views = @View(title = "描述"),
            edit = @Edit(title = "描述", notNull = true)
    )
    private String roomDescription;

}