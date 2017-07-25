package kz.allpay.soap.demo;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import kz.allpay.mfs.ws.soap.generated.v1_0.CompleteTransactionResponse;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */

public class DataBase {

    private static Type myType = new TypeToken<HashMap<String, CompleteTransactionResponse>>() {}.getType();

    private static final Map<String, CompleteTransactionResponse> responseDatabase = new HashMap<>();

    private static final String STORAGE_FILE_FULL_NAME = "/tmp/storageFile.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(XMLGregorianCalendar.class, new XMLGregorianCalendarConverter.Serializer())
            .registerTypeAdapter(XMLGregorianCalendar.class, new XMLGregorianCalendarConverter.Deserializer())
            .create();

    public static Map<String, CompleteTransactionResponse> getResponseDatabase() {
        return responseDatabase;
    }

    public static void writeToStorageFile() throws IOException {
        try (final PrintWriter writer = new PrintWriter(STORAGE_FILE_FULL_NAME)) {
            final String mapAsString = gson.toJson(responseDatabase);
            writer.println(mapAsString);
        }
    }

    public static void readFromStorageFile() throws IOException {
        final File storageFile = new File(STORAGE_FILE_FULL_NAME);

        if (!storageFile.exists()) {
            return;
        }

        try (final Reader fileReader = new InputStreamReader(new FileInputStream(storageFile), "UTF-8")) {
            final JsonReader reader = new JsonReader(fileReader);
            final Map<String, CompleteTransactionResponse> databaseFile = gson.fromJson(reader, myType);
            if (databaseFile != null) {
                responseDatabase.putAll(databaseFile);
            }
        }
    }

    /**
     * https://github.com/google/gson/issues/368
     */
    public static class XMLGregorianCalendarConverter {
        public static class Serializer implements JsonSerializer {
            public Serializer() {
                super();
            }
            public JsonElement serialize(Object t, Type type,
                                         JsonSerializationContext jsonSerializationContext) {
                XMLGregorianCalendar xgcal = (XMLGregorianCalendar) t;
                return new JsonPrimitive(xgcal.toXMLFormat());
            }
        }
        public static class Deserializer implements JsonDeserializer {
            public Object deserialize(JsonElement jsonElement, Type type,
                                      JsonDeserializationContext jsonDeserializationContext) {
                try {
                    return DatatypeFactory.newInstance().newXMLGregorianCalendar(
                            jsonElement.getAsString());
                } catch (Exception e) {
                    return null;
                }
            }
        }
    }
}
