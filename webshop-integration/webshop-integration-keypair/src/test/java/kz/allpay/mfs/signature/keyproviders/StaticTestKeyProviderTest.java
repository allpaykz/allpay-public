package kz.allpay.mfs.signature.keyproviders;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StaticTestKeyProviderTest {

    @Test
    public void testGetPublicKey() throws Exception {
        Assert.assertNotNull(new StaticTestKeyProvider().getPublicKey(null));
    }
}