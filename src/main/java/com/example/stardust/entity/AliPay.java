package com.example.stardust.entity;

import lombok.Data;

import java.util.Objects;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 21:47
 */
@Data
public class AliPay {
    private String traceNo;
    private double totalAmount;
    private String subject;
    private String alipayTraceNo;

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAlipayTraceNo() {
        return alipayTraceNo;
    }

    public void setAlipayTraceNo(String alipayTraceNo) {
        this.alipayTraceNo = alipayTraceNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AliPay aliPay)) {
            return false;
        }
        return Double.compare(aliPay.getTotalAmount(), getTotalAmount()) == 0 && Objects.equals(getTraceNo(), aliPay.getTraceNo()) && Objects.equals(getSubject(), aliPay.getSubject()) && Objects.equals(getAlipayTraceNo(), aliPay.getAlipayTraceNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTraceNo(), getTotalAmount(), getSubject(), getAlipayTraceNo());
    }

    @Override
    public String toString() {
        return "AliPay{" +
                "traceNo='" + traceNo + '\'' +
                ", totalAmount=" + totalAmount +
                ", subject='" + subject + '\'' +
                ", alipayTraceNo='" + alipayTraceNo + '\'' +
                '}';
    }
}
