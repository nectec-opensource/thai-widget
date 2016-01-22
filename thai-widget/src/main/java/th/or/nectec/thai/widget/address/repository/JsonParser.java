package th.or.nectec.thai.widget.address.repository;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class JsonParser {

    public static <T> ArrayList<T> parse(Context context, String jsonFileName, Class<T> tClass) {

        ArrayList<T> allProvince = new ArrayList<>();
        Gson gson = new Gson();
        try {
            InputStream inputStream = context.getAssets().open(jsonFileName);
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            reader.beginArray();
            while (reader.hasNext()) {
                T message = gson.fromJson(reader, tClass);
                allProvince.add(message);
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allProvince;
    }
}
