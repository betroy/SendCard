package com.troy.sendcard.bean.response;

import java.util.List;

/**
 * Created by chenlongfei on 16/4/12.
 */
public class HandledResult {

    /**
     * code : 200
     * msg : success
     * data : [{"apply_id":"3","phone":"13163303303","receive_name":"李慧","province":"100063","city":"152032","send_no":"9016080878","send_type":"2","card_no_list":["1000119000001605611"],"card_no_total":1},{"apply_id":"6","phone":"13163303404","receive_name":"刘德华","province":"123131","city":"123123","send_no":"6371039979","send_type":"1","card_no_list":["1000119000001832323","1000119000001832324","1000119000001832325"],"card_no_total":3}]
     */

    private int code;
    private String msg;
    /**
     * apply_id : 3
     * phone : 13163303303
     * receive_name : 李慧
     * province : 100063
     * city : 152032
     * send_no : 9016080878
     * send_type : 2
     * card_no_list : ["1000119000001605611"]
     * card_no_total : 1
     */

    private List<HandledDataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<HandledDataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<HandledDataEntity> getData() {
        return data;
    }

    public static class HandledDataEntity {
        private String apply_id;
        private String phone;
        private String receive_name;
        private String province;
        private String city;
        private String send_no;
        private String send_type;
        private int card_no_total;
        private List<String> card_no_list;

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

        public void setSend_no(String send_no) {
            this.send_no = send_no;
        }

        public void setSend_type(String send_type) {
            this.send_type = send_type;
        }

        public void setCard_no_total(int card_no_total) {
            this.card_no_total = card_no_total;
        }

        public void setCard_no_list(List<String> card_no_list) {
            this.card_no_list = card_no_list;
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

        public String getSend_no() {
            return send_no;
        }

        public String getSend_type() {
            return send_type;
        }

        public int getCard_no_total() {
            return card_no_total;
        }

        public List<String> getCard_no_list() {
            return card_no_list;
        }
    }
}
