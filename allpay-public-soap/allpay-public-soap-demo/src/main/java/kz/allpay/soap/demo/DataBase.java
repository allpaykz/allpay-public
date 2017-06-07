package kz.allpay.soap.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import kz.allpay.mfs.ws.soap.generated.v1_0.CompleteTransactionResponse;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Sanzhar Aubakirov
 * Date: 12/6/16
 */

public class DataBase {

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
        BufferedReader reader = new BufferedReader(new FileReader(storageFile));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null){
            builder.append(line).append("\n");
        }
        reader.close();
        String jsonDic = builder.toString();
        Type myType = new TypeToken<HashMap<String, CompleteTransactionResponse>>() {}.getType();
        responseDatabase.putAll(gson.fromJson(jsonDic, myType));
    }
}
