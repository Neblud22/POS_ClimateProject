package raingroup.climaterainproject.IO;

import com.fasterxml.jackson.databind.ObjectMapper;
import raingroup.climaterainproject.Dto.NasaResponse;

import java.io.File;

public class FileReader {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NasaResponse read(String fileName) throws Exception {

        File file = new File("output/" + fileName);

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + file.getAbsolutePath());
        }

        return objectMapper.readValue(file, NasaResponse.class);
    }
}
