package com.cateam.pki.tms.utils;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
/**
 * @author ThucCD
 */

public class ConstantValue {
    @Getter
    public enum ErrorCode {
        UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
        USER_EXISTED(1000, "User existed", HttpStatus.BAD_REQUEST),
        USER_NOT_EXISTED(1001, "User not existed", HttpStatus.NOT_FOUND),
        UNAUTHENTICATED(1002, "Unauthenticated", HttpStatus.UNAUTHORIZED),
        UNAUTHORIZED(1003, "You do not have permission", HttpStatus.FORBIDDEN),
        ROLE_NOT_FOUND(1004,"Role is not found.",HttpStatus.NOT_FOUND),
        INVALID_SIGNATURE_JWT(1005,"Invalid JWT signature",HttpStatus.UNAUTHORIZED),
        JWT_EXPIRED(1006,"JWT token is expired",HttpStatus.UNAUTHORIZED),
        JWT_VALIDATE_ERROR(1007,"JWT token validation error",HttpStatus.UNAUTHORIZED),
        JWT_CREATE_ERROR(1008,"JWT token creation error",HttpStatus.FORBIDDEN)
        ;
        ErrorCode(int code, String message, HttpStatusCode statusCode) {
            this.code = code;
            this.message = message;
            this.statusCode = statusCode;
        }

        private final int code;
        private final String message;
        private final HttpStatusCode statusCode;
    }
}
