package com.example.stardust.entity.Trade;

import com.example.stardust.entity.BaseEntity;

import java.util.Date;
import java.util.Objects;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/28 9:49
 */
public class SteelTrade extends BaseEntity {
    /**
     * 钢板编号
     */
    private String steelId;
    /**
     * 交易时间
     */
    private Date tradeTime;
    /**
     * 买方
     */
    private String buyer;
    /**
     * 卖方
     */
    private String seller;
    /**
     * 交易数量
     */
    private int quantity;
    /**
     * 交易价格
     */
    private double price;

    public String getSteelId() {
        return steelId;
    }

    public void setSteelId(String steelId) {
        this.steelId = steelId;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SteelTrade that)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return getQuantity() == that.getQuantity() && Double.compare(that.getPrice(), getPrice()) == 0 && Objects.equals(getSteelId(), that.getSteelId()) && Objects.equals(getTradeTime(), that.getTradeTime()) && Objects.equals(getBuyer(), that.getBuyer()) && Objects.equals(getSeller(), that.getSeller());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSteelId(), getTradeTime(), getBuyer(), getSeller(), getQuantity(), getPrice());
    }

    @Override
    public String toString() {
        return "SteelTrade{" +
                "steelId='" + steelId + '\'' +
                ", tradeTime=" + tradeTime +
                ", buyer='" + buyer + '\'' +
                ", seller='" + seller + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}