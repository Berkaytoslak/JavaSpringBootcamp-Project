package com.graduationProject.graduationProject.utils;

import com.graduationProject.graduationProject.exceptions.BadRequestException;

public class WalletValidatorUtil {
    public static void validateCreditScore(int creditScore) {
        if(creditScore < 0){
            throw new BadRequestException(ErrorMessageConstants.CREDIT_SCORE_IS_MINUS);
        }
    }

}
