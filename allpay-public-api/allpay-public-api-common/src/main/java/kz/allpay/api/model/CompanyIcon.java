package kz.allpay.api.model;

import java.io.Serializable;

/**
 * Created by aigerim on 9/3/18.
 */
public class CompanyIcon implements Serializable {

    private byte[] bytes;
    private String mimeType;

    public CompanyIcon() {
    }

    public CompanyIcon(byte[] bytes, String mimeType) {
        this.bytes = bytes;
        this.mimeType = mimeType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
