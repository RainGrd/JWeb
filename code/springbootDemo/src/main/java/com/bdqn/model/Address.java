package com.bdqn.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hengwang
 * @date 2021/11/7 19:04
 */
@Component
@ConfigurationProperties(prefix = "address")
public class Address {

    private int id;

    private String addressDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressDetail='" + addressDetail + '\'' +
                '}';
    }
}
