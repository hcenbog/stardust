package com.example.stardust.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/12 20:23
 */
public class Product extends BaseEntity implements Serializable {
    private Integer id;
    private Integer catId;
    private String name;
    private Integer hotSteel;
    private String texture;
    private String size;
    private String sizeInfo;
    private String qi;
    private String quantity;
    private String weight;
    private Long price;
    private String purpose;
    private Integer stock;
    private String despatch;
    private String despatchWh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHotSteel() {
        return hotSteel;
    }

    public void setHotSteel(Integer hotSteel) {
        this.hotSteel = hotSteel;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSizeInfo() {
        return sizeInfo;
    }

    public void setSizeInfo(String sizeInfo) {
        this.sizeInfo = sizeInfo;
    }

    public String getQi() {
        return qi;
    }

    public void setQi(String qi) {
        this.qi = qi;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDespatch() {
        return despatch;
    }

    public void setDespatch(String despatch) {
        this.despatch = despatch;
    }

    public String getDespatchWh() {
        return despatchWh;
    }

    public void setDespatchWh(String despatchWh) {
        this.despatchWh = despatchWh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getCatId(), product.getCatId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getHotSteel(), product.getHotSteel()) && Objects.equals(getTexture(), product.getTexture()) && Objects.equals(getSize(), product.getSize()) && Objects.equals(getSizeInfo(), product.getSizeInfo()) && Objects.equals(getQi(), product.getQi()) && Objects.equals(getQuantity(), product.getQuantity()) && Objects.equals(getWeight(), product.getWeight()) && Objects.equals(getPrice(), product.getPrice()) && Objects.equals(getPurpose(), product.getPurpose()) && Objects.equals(getStock(), product.getStock()) && Objects.equals(getDespatch(), product.getDespatch()) && Objects.equals(getDespatchWh(), product.getDespatchWh());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getCatId(), getName(), getHotSteel(), getTexture(), getSize(), getSizeInfo(), getQi(), getQuantity(), getWeight(), getPrice(), getPurpose(), getStock(), getDespatch(), getDespatchWh());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", catId=" + catId +
                ", name='" + name + '\'' +
                ", hotSteel=" + hotSteel +
                ", texture='" + texture + '\'' +
                ", size='" + size + '\'' +
                ", sizeInfo='" + sizeInfo + '\'' +
                ", qi='" + qi + '\'' +
                ", quantity='" + quantity + '\'' +
                ", weight='" + weight + '\'' +
                ", price=" + price +
                ", purpose='" + purpose + '\'' +
                ", stock=" + stock +
                ", despatch='" + despatch + '\'' +
                ", despatchWh='" + despatchWh + '\'' +
                '}';
    }
}
