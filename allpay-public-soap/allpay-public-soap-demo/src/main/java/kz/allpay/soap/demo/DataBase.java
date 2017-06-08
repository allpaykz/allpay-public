package kz.allpay.soap.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */

public class DataBase {

    private static Type myType = new TypeToken<HashMap<String, CompleteTransactionResponse>>() {}.getType();

    private static final Map<String, CompleteTransactionResponse> responseDatabase = new HashMap<>();

    private static final String STORAGE_FILE_FULL_NAME = "/tmp/storageFile.json";
    private static final Gson gson = new GsonBuilder().create();

    public static Map<String, CompleteTransactionResponse> getResponseDatabase() {
        return responseDatabase;
    }

    public static void writeToStorageFile() throws IOException {
        final PrintWriter writer = new PrintWriter(STORAGE_FILE_FULL_NAME);
        final String mapAsString = gson.toJson(responseDatabase);
        writer.println(mapAsString);
        writer.close();
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
}
