package com.vod.jdb.exception;

public enum ErrorCode {

    // Common
    RTN_SUCCESS(400, "0000", "RTN_SUCCESS"),
    RTN_PDU_ERR(405, "9001", "RTN_PDU_ERR"),
    RTN_DB_CON_ERR(50, "9005", "RTN_DB_CON_ERR"),
    RTN_DB_ERR(500, "9006", "RTN_DB_ERR"),
    RTN_BAD_REQ_ERR(500, "9007", "RTN_BAD_REQ_ERR"),
    RTN_REQ_MTH_ERR(500, "9008", "RTN_REQ_MTH_ERR"),
    RTN_CON_TYPE_ERR(500, "9009", "RTN_CON_TYPE_ERR"),
    RTN_UNKN_ERR(500, "9010", "RTN_UNKN_ERR"),
    RTN_NET_CON_ERR(500, "9011", "RTN_NET_CON_ERR"),
    RTN_NOT_FOU_ERR(500, "9014", "RTN_NOT_FOU_ERR"),
    RTN_PROV_ERR(500, "9015", "RTN_PROV_ERR"),
    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}
