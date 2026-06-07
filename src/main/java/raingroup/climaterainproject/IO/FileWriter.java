package raingroup.climaterainproject.IO;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class FileWriter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String save(Parameter parameter, String jsonResponse) throws Exception {

        // folder
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        // object mapper
        Object json = objectMapper.readValue(jsonResponse, Object.class);
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

        // write
        File file = new File(parameter.toFileName());
        java.io.FileWriter writer = new java.io.FileWriter(file);
        writer.write(prettyJson);
        writer.close();

        return "Saved file: " + file.getAbsolutePath();
    }
}
