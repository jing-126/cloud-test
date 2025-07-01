package com.jing.cloud.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 表名：t_storage
 * 表注释：库存表
*/
@Table(name = "t_storage")
@Data
public class Storage  implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 总库存
     */
    private Long total;

    /**
     * 已用库存
     */
    private Long used;

    /**
     * 剩余库存
     */
    private Long residue;
}