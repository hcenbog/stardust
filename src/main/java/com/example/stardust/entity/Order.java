package com.example.stardust.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 15:30
 */
public class Order extends BaseEntity implements Serializable {
    private Integer oid;
    private Integer uid;
    private String orderId;
    private String buyer;
    private String seller;
    private BigInteger totalPrice;
    private BigInteger num;
    private Integer status;
    private Date orderTime;
    private Date payTime;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public BigInteger getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigInteger totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigInteger getNum() {
        return num;
    }

    public void setNum(BigInteger num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getOid(), order.getOid()) && Objects.equals(getUid(), order.getUid()) && Objects.equals(getOrderId(), order.getOrderId()) && Objects.equals(getBuyer(), order.getBuyer()) && Objects.equals(getSeller(), order.getSeller()) && Objects.equals(getTotalPrice(), order.getTotalPrice()) && Objects.equals(getNum(), order.getNum()) && Objects.equals(getStatus(), order.getStatus()) && Objects.equals(getOrderTime(), order.getOrderTime()) && Objects.equals(getPayTime(), order.getPayTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOid(), getUid(), getOrderId(), getBuyer(), getSeller(), getTotalPrice(), getNum(), getStatus(), getOrderTime(), getPayTime());
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", orderId='" + orderId + '\'' +
                ", buyer='" + buyer + '\'' +
                ", seller='" + seller + '\'' +
                ", totalPrice=" + totalPrice +
                ", num=" + num +
                ", status=" + status +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                '}';
    }
}
