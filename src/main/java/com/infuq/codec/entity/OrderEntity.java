package com.infuq.codec.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = -7345300399512798560L;

    private String            id;

    private String            no;

    private String            thirdNo;

    private String            userId;

    private Integer           orderType;

    private Integer           businessType;

    private String            businessCategory;

    private Integer           sendType;

    private Integer           source;

    private Integer           channel;

    private Integer           status;

    private Integer           noticeStatus;     

    private Long              orderTag;

    private Long              taskTag;
    
    private String            taskTagDesc;

    private Long              businessTag;

    private Integer           payStatus;

    private Integer           rateStatus;

    private Integer           auditStatus;        

    private Integer           preSaleModel;

    private Date              deliveryBeginTime;

    private Date              deliveryEndTime;

    private Integer           deliveryEndDay;

    private Integer           payKind;

    private Integer           payChannel;
    
    private String            payNo;

    private String            billNo;

    private Long              marketCost;

    private Long              originalCost;

    private Long              saleCost;

    private Long              realCost;

    private Long              logisticsCost;

    private String            buyerName;

    private String            buyerPhone;

    private String            buyerTelephone;

    private String            virtualPhone;

    private String            userName;

    private String            thirdUserName;

    private String            shopName;

    private String            shopCode;

    private Integer           afterSaleFlag;

    private String            sourceChannel;

    private String            drainageChannel;

    private Integer           invoiceFlag;

    private String            supplierCode;

    private Integer           reportStatus;

    private String            cardName;

    private String            lng;
    
    private String            lat;

    private String            imei;

    private String            ip;

    private Date              buyTime;

    private Date              payTime;

    private Date              createTime;

    private Date              updateTime;

    private String            createPerson;

    private String            updatePerson;

    private String 			  owner;

    private String            orderWay;

    private String            firstChannel;

    private String            secondChannel;

    private Integer		      dispatchProcessStatus;

    private Integer			  isDeleted;

    private String            parentNo;

    private String 			  uuid;

    private String            channelCode;

    private String            shopStoreId;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}