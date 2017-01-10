package kz.allpay.mfs.ws.soap.exception;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InsufficientPrivilegeExceptionTest {

    @Test
    public void testGetMessage() throws Exception {
        String localizedMessage = new InsufficientPrivilegeException("77072734954", "privilege1").getLocalizedMessage();
        System.out.println("localizedMessage = " + localizedMessage);
        Assert.assertNotNull(localizedMessage, "localized message must not be null");
    }
}