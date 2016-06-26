package com.example.lufei.retrofitsample.entity;

/**
 * Created by lufei on 6/26/16.
 */
public class IPResult {
    /**
     * code : 0
     * data : {"country":"中国","country_id":"CN","area":"华北","area_id":"100000","region":"天津市","region_id":"120000","city":"天津市","city_id":"120100","county":"","county_id":"-1","isp":"移动","isp_id":"100025","ip":"111.32.23.2"}
     */

    public int code;
    /**
     * country : 中国
     * country_id : CN
     * area : 华北
     * area_id : 100000
     * region : 天津市
     * region_id : 120000
     * city : 天津市
     * city_id : 120100
     * county :
     * county_id : -1
     * isp : 移动
     * isp_id : 100025
     * ip : 111.32.23.2
     */

    public DataBean data;

    public static class DataBean {
        public String country;
        public String country_id;
        public String area;
        public String area_id;
        public String region;
        public String region_id;
        public String city;
        public String city_id;
        public String county;
        public String county_id;
        public String isp;
        public String isp_id;
        public String ip;
    }
}
