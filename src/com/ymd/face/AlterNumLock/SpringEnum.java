package com.ymd.face.AlterNumLock;

public enum SpringEnum {
    ONE(1,"春"),TWO(2,"夏"),THREE(3,"秋"),FOUR(4,"冬");
    private Integer retCode;
    private String retSpring;

    SpringEnum() {
    }

    SpringEnum(Integer retCode, String retSpring) {
        this.retCode = retCode;
        this.retSpring = retSpring;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetSpring() {
        return retSpring;
    }

    public void setRetSpring(String retSpring) {
        this.retSpring = retSpring;
    }
}
