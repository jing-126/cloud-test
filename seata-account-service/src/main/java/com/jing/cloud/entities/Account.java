package com.jing.cloud.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：t_account
 * 表注释：账户表
*/
@Table(name = "t_account")
public class Account {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 总额度
     */
    private Long total;

    /**
     * 已用账户余额
     */
    private Long used;

    /**
     * 剩余可用额度
     */
    private Long residue;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return userId - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取总额度
     *
     * @return total - 总额度
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 设置总额度
     *
     * @param total 总额度
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * 获取已用账户余额
     *
     * @return used - 已用账户余额
     */
    public Long getUsed() {
        return used;
    }

    /**
     * 设置已用账户余额
     *
     * @param used 已用账户余额
     */
    public void setUsed(Long used) {
        this.used = used;
    }

    /**
     * 获取剩余可用额度
     *
     * @return residue - 剩余可用额度
     */
    public Long getResidue() {
        return residue;
    }

    /**
     * 设置剩余可用额度
     *
     * @param residue 剩余可用额度
     */
    public void setResidue(Long residue) {
        this.residue = residue;
    }
}