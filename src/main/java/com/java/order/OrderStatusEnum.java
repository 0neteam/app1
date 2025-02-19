package com.java.order;

public enum OrderStatusEnum {
    PENDING(1, "발주대기"),
    IN_PROGRESS(2, "발주취소"),
    COMPLETED(3, "발주확정");

    private final int code;
    private final String description;

    OrderStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    // 숫자 값을 받으면 해당하는 문자열 리턴
    public static String getDescriptionByCode(int code) {
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
            if (status.code == code) {
                return status.description;
            }
        }
        return "알 수 없음";
    }

}
