/**
 *
 */
package com.bccard.template.common.consts;

/**
 * @packageName : com.hoban.common
 * @fileName    : ErrorCode.java
 * @author      : hslee
 * @date        : 2021.11.30
 * @description :
 * ===========================================================
 * DATE                       AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.30                 hslee       최초 생성
 */
public enum ErrorCode {

    // Common
    /** Invalid Input Value(E400) */
    INVALID_TYPE_VALUE(400, "40000", "Invalid Type Value"),
    BAD_REQUEST(400, "40001", "Invalid Type Value"),

    /** Invalid Token Expired(E401) */
    INVALID_TOKEN_EXPIRED(401, "40100", "Invalid Token Expired"),
    /** Access is Denied(E403) */
    HANDLE_ACCESS_DENIED(403, "40300", "Access is Denied"),
    /** Not Found(E404) */
    FILE_NOT_FOUND(404, "40400", "File Not Found"),
    /** Method Not Allowed(E405) */
    METHOD_NOT_ALLOWED(405, "40500", "Method Not Allowed"),

    /** Server Error(E500) */
    INTERNAL_SERVER_ERROR(500, "50000", "Server Error"),
    INTERNAL_SERVER_ERROR1(500, "50001", "데이터 변환에러");


    /** 코드값 */
    private final String code;
    /** 메세지값 */
    private final String message;
    /** status 값 */
    private int status;

    /**
     *
     * @param status
     * @param code
     * @param message
     */
    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    /**
     * 메세지 조회
     * @return
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 코드 조회
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * Status 조회
     * @return
     */
    public int getStatus() {
        return status;
    }
}
