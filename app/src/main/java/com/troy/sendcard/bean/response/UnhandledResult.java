package com.troy.sendcard.bean.response;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class UnhandledResult {

    /**
     * code : 200
     * msg : 注册成功
     * data : [{"apply_id":"2","phone":"13163303101","receive_name":"卢杰","province":"121124","city":"121212","card_no_total":"1","is_handling":false},{"apply_id":"3","phone":"13163303202","receive_name":"李超","province":"100063","city":"152032","card_no_total":"2","is_handling":true}]
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
     * is_handling : false
     */

    private List<UnhandledDataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<UnhandledDataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<UnhandledDataEntity> getData() {
        return data;
    }

    public static class UnhandledDataEntity {
        private String apply_id;
        private String phone;
        private String receive_name;
        private String province;
        private String city;
        private String card_no_total;
        private boolean is_handling;

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

        public void setIs_handling(boolean is_handling) {
            this.is_handling = is_handling;
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

        public boolean isIs_handling() {
            return is_handling;
        }
    }
}
