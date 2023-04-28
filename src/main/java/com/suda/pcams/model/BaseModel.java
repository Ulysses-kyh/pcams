package com.suda.pcams.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.EruptField;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * BaseModel，用于被继承，目前只有主键id
 * 采用原生id没有使用uuid，方便进行测试
 *
 * @author Ulysses
 * @since 2023-04-23
 */
public class BaseModel {
    //主键
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id;
}

