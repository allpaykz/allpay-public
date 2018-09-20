package kz.allpay.api.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aigerim on 8/24/18.
 */
public class ServicePublicInfoResponse extends AbstractResponse implements Serializable {

    private List<ServicePublicInfo> servicePublicInfoList;

    public List<ServicePublicInfo> getServicePublicInfoList() {
        return servicePublicInfoList;
    }

    public void setServicePublicInfoList(List<ServicePublicInfo> servicePublicInfoList) {
        this.servicePublicInfoList = servicePublicInfoList;
    }
}
