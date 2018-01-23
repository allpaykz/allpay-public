
### update-api.sh

update-api.sh - this file can be used to generate a swagger java client for the mfs project. It generates all swagger based api

#### Usage

```bash
cd /to/your/project/dir
bash <(curl -s https://raw.githubusercontent.com/allpaykz/allpay-public/develop/scripts/update-api.sh) http://alpha.allpay.kz/mfs $(pwd)
```

Then you need to introduce these changes:

```diff
--- a/pom.xml
+++ b/pom.xml
@@ -11,6 +11,15 @@
 
     <properties>
         <java.version>1.8</java.version>
+        <maven.compiler.source>${java.version}</maven.compiler.source>
+        <maven.compiler.target>${java.version}</maven.compiler.target>
+        <swagger-core-version>1.5.15</swagger-core-version>
+        <okhttp-version>2.7.5</okhttp-version>
+        <gson-version>2.8.1</gson-version>
+        <jodatime-version>2.9.9</jodatime-version>
+        <maven-plugin-version>1.0.0</maven-plugin-version>
+        <junit-version>4.12</junit-version>
+        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
     </properties>
 
     <dependencies>
@@ -61,6 +70,39 @@
             <version>1.7.5</version>
         </dependency>
 
+        <!-- generated -->
+        <dependency>
+            <groupId>io.swagger</groupId>
+            <artifactId>swagger-annotations</artifactId>
+            <version>${swagger-core-version}</version>
+        </dependency>
+        <dependency>
+            <groupId>com.squareup.okhttp</groupId>
+            <artifactId>okhttp</artifactId>
+            <version>${okhttp-version}</version>
+        </dependency>
+        <dependency>
+            <groupId>com.squareup.okhttp</groupId>
+            <artifactId>logging-interceptor</artifactId>
+            <version>${okhttp-version}</version>
+        </dependency>
+        <dependency>
+            <groupId>com.google.code.gson</groupId>
+            <artifactId>gson</artifactId>
+            <version>${gson-version}</version>
+        </dependency>
+        <dependency>
+            <groupId>joda-time</groupId>
+            <artifactId>joda-time</artifactId>
+            <version>${jodatime-version}</version>
+        </dependency>
+        <!-- test dependencies -->
+        <dependency>
+            <groupId>junit</groupId>
+            <artifactId>junit</artifactId>
+            <version>${junit-version}</version>
+            <scope>test</scope>
+        </dependency>
     </dependencies>
```

example of api client
```java
/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 18.11.17 16:03
 */
public class ApiClientFactory {
    public ApiClient getApiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.getJSON().setGson(new GsonBuilder()
                        .registerTypeAdapter(Date.class, new MyDateTypeAdapter())
                        .registerTypeAdapter(DateTime.class, new MyDateTypeAdapterJoda())
                        .registerTypeAdapter(LocalDate.class, new MyDateTypeAdapterJodaLocal())
                        .create()
        );
        apiClient.setLenientOnJson(true);
        apiClient.setVerifyingSsl(false);
        apiClient.setBasePath("http://127.0.0.1:8080/mfs/webresources");
        return apiClient;
    }

    public class MyDateTypeAdapter extends TypeAdapter<Date> {
        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            if (value == null)
                out.nullValue();
            else
                out.value(value.getTime() / 1000);
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            if (in != null) {
                long nextLong = in.nextLong();
                System.out.println("nextLong = " + nextLong);
                return new Date(nextLong / 1000);
            }
            else
                return null;
        }
    }

    public class MyDateTypeAdapterJoda extends TypeAdapter<DateTime> {
        private final MyDateTypeAdapter delegate = new MyDateTypeAdapter();
        @Override
        public void write(JsonWriter out, DateTime value) throws IOException {
            delegate.write(out, value.toDate());
        }

        @Override
        public DateTime read(JsonReader in) throws IOException {
            return new DateTime(delegate.read(in));
        }
    }

    public class MyDateTypeAdapterJodaLocal extends TypeAdapter<LocalDate> {
        private final MyDateTypeAdapter delegate = new MyDateTypeAdapter();
        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            delegate.write(out, value.toDate());
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            return new LocalDate(delegate.read(in));
        }
    }
}
```

example of service impl:

```java
@Service
public class SmsSenderService {

    private final IntegrationApiCredentialProvider integrationApiCredentialProvider;
    private final ApiClient apiClient = new ApiClientFactory().getApiClient();
    private final IntegrationApiServiceApi integrationApiServiceApi = new IntegrationApiServiceApi(apiClient);

    public SmsSenderService(IntegrationApiCredentialProvider integrationApiCredentialProvider) {
        this.integrationApiCredentialProvider = integrationApiCredentialProvider;
    }

    public boolean sendSms(String phoneToSendTo, String text) throws ApiException {
        StringResponse response = integrationApiServiceApi.sendSms(phoneToSendTo, text,
                integrationApiCredentialProvider.getCertificateNumber(),
                integrationApiCredentialProvider.getAuthorization(),
                integrationApiCredentialProvider.getRequesterLoginName());
        return response.getResult().equals("true");
    }
}
```