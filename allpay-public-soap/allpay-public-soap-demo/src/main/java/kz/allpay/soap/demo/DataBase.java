package kz.allpay.soap.demo;

import kz.allpay.mfs.ws.soap.generated.v1_0.CompleteTransactionResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */

public class DataBase {

    private static final Map<String, CompleteTransactionResponse> responseDatabase = new HashMap<>();

    public static Map<String, CompleteTransactionResponse> getResponseDatabase() {
        return responseDatabase;
    }
}
