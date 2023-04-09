package com.neu.mybatisdemo1.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 * 
 * @TableName helplist
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Helplist implements Serializable {
    /**
     * 
     */
    private Integer listId;

    /**
     * 求助状态  默认0未被帮助 1已处理
     */
    private Integer donationState;

    /**
     * 受捐者
     */
    private Integer recipientId;

    /**
     * 捐赠者
     */
    private Integer giverId;

    /**
     * 需求
     */
    private String donationDemand;

    /**
     * 创建日期
     */
    private String createDate;

    /**
     * 完成日期
     */
    private String finishDate;

    /**
     * 物流信息
     */
    private String logisticsInformation;

    /**
     * 受捐者反馈
     */
    private String recipientResponse;

    private String projectOrganizer;

    private static final long serialVersionUID = 1L;
}