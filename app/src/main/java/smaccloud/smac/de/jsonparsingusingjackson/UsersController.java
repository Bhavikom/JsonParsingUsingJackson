package smaccloud.smac.de.jsonparsingusingjackson;

import android.os.Environment;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by S Soft on 09-Jan-18.
 */

public class UsersController {

    private static final String DL_URL = "{\n" +
            "    \"Users\": [\n" +
            "        {\n" +
            "            \"firstname\": \"Nazim\",\n" +
            "            \"lastname\": \"Benbourahla\",\n" +
            "            \"login\": \"n_benbourahla\",\n" +
            "            \"twitter\": \"@n_benbourahla\",\n" +
            "            \"web\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"firstname\": \"Tutos\",\n" +
            "            \"lastname\": \"android\",\n" +
            "            \"login\": \"Tutos-android\",\n" +
            "            \"twitter\": \"\",\n" +
            "            \"web\": \"www.tutos-android.com\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private ObjectMapper objectMapper = null;
    private JsonFactory jsonFactory = null;
    private JsonParser jp = null;
    private ArrayList<User> userList = null;
    private Users users = null;
    private File jsonOutputFile;
    private File jsonFile;

    public UsersController() {
        objectMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }

    public void init() {
        downloadJsonFile();
        try {
            jp = jsonFactory.createJsonParser(DL_URL);
            users = objectMapper.readValue(jp, Users.class);
            userList = users.get("Users");
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadJsonFile() {
        try {
            createFileAndDirectory();
            URL url = new URL(UsersController.DL_URL);
            HttpURLConnection urlConnection;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            FileOutputStream fileOutput = new FileOutputStream(jsonFile);
            InputStream inputStream = urlConnection.getInputStream();
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
            }
            fileOutput.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFileAndDirectory() throws FileNotFoundException {
        final String extStorageDirectory = Environment
                .getExternalStorageDirectory().toString();
        final String meteoDirectory_path = extStorageDirectory + "/tutos-android";
        jsonOutputFile = new File(meteoDirectory_path, "/");
        if (jsonOutputFile.exists() == false)
            jsonOutputFile.mkdirs();
        jsonFile = new File(jsonOutputFile, "users.json");
    }

    public ArrayList<User> findAll() {
        return userList;
    }

    public User findById(int id) {
        return userList.get(id);
    }

}
