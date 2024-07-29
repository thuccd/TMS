package com.cateam.pki.tms.exception;
import com.cateam.pki.tms.utils.ConstantValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @author ThucCD
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {
    public ApiException(ConstantValue.ErrorCode enumCode) {
        super(enumCode.getMessage());
        this.code = enumCode;
    }

    private ConstantValue.ErrorCode code;
}
