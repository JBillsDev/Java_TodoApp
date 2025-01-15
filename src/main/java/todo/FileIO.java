package todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileIO {
    public static void writeFile(final ArrayList<EntryData> entries) {
        var objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("data.dat"), entries);
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    public static ArrayList<EntryData> readFile() {
        if (!new File("data.dat").exists()) return new ArrayList<>();

        var objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File("data.dat"), objectMapper.getTypeFactory()
                    .constructCollectionType(ArrayList.class, EntryData.class));
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
