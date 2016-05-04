package com.troy.sendcard.bean.respone;

import java.io.Serializable;

/**
 * Created by chenlongfei on 16/4/13.
 */
public class SendCardInfoResult {

    /**
     * code : 200
     * msg : SUCCESS
     * data : {"apply_id":"2","phone":"13163303101","receive_name":"卢杰","province":"121124","city":"121212","card_no_total":"1"}
     */

    private int code;
    private String msg;
    /**
     * apply_id : 2
     * phone : 13163303101
     * receive_name : 卢杰
     * province : 121124
     * city : 121212
     * card_no_total : 1
     */

    private SendCardInfoDataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(SendCardInfoDataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SendCardInfoDataEntity getData() {
        return data;
    }

    public static class SendCardInfoDataEntity implements Serializable {
        private String apply_id;
        private String phone;
        private String receive_name;
        private String province;
        private String city;
        private String address;
        private String card_no_total;

        public void setApply_id(String apply_id) {
            this.apply_id = apply_id;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setReceive_name(String receive_name) {
            this.receive_name = receive_name;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCard_no_total(String card_no_total) {
            this.card_no_total = card_no_total;
        }

        public String getApply_id() {
            return apply_id;
        }

        public String getPhone() {
            return phone;
        }

        public String getReceive_name() {
            return receive_name;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getCard_no_total() {
            return card_no_total;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
