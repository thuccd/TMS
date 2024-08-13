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
        UNCATEGORIZED_EXCEPTION(21, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
        USER_EXISTED(22, "User existed", HttpStatus.BAD_REQUEST),
        USER_NOT_EXISTED(23, "User not existed", HttpStatus.NOT_FOUND),
        UNAUTHENTICATED(24, "Unauthenticated", HttpStatus.UNAUTHORIZED),
        UNAUTHORIZED(25, "You do not have permission", HttpStatus.FORBIDDEN),
        ROLE_NOT_FOUND(26,"Role is not found.",HttpStatus.NOT_FOUND),
        INVALID_SIGNATURE_JWT(27,"Invalid JWT signature",HttpStatus.UNAUTHORIZED),
        JWT_EXPIRED(28,"JWT token is expired",HttpStatus.UNAUTHORIZED),
        JWT_VALIDATE_ERROR(29,"JWT token validation error",HttpStatus.UNAUTHORIZED),
        JWT_CREATE_ERROR(30,"JWT token creation error",HttpStatus.FORBIDDEN),
        ROLE_EXISTED(31,"role existed ",HttpStatus.BAD_REQUEST)
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
