package kz.allpay.soap.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.allpay.mfs.ws.soap.generated.v1_0.CompleteTransactionResponse;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */
@Stateless
@Path("api")
public class TransactionManagment {

    private static final Gson gson = new GsonBuilder().create();

    @GET
    @Path("transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public String transactions() {
        return gson.toJson(DataBase.getResponseDatabase());
    }


}
