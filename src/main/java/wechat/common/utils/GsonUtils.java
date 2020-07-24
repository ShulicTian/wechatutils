package wechat.common.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtils {
    private static JsonParser jsonParser = new JsonParser();

    public static JsonObject parseJsonObject(String jsonString) {
        JsonElement jsonElement = jsonParser.parse(jsonString);
        return jsonElement.getAsJsonObject();
    }

    public static JsonArray parseJsonArray(String jsonString) {
        JsonElement jsonElement = jsonParser.parse(jsonString);
        return jsonElement.getAsJsonArray();
    }

}
