package raingroup.climaterainproject.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import raingroup.climaterainproject.IO.FileWriter;
import raingroup.climaterainproject.IO.Parameter;

@Service
public class Rain_Service {

    private final RestClient restClient;
    private final FileWriter fileWriter;

    public Rain_Service() {
        this.restClient = RestClient.create("https://power.larc.nasa.gov");
        this.fileWriter = new raingroup.climaterainproject.IO.FileWriter();
    }

    public String fetchAndSave(String start, String end, String params, double longitude, double latitude) throws Exception {

        // object with all the params
        Parameter parameter = new Parameter(start, end, params, longitude, latitude);

        // fetch
        String jsonResponse = this.restClient.get()
                .uri(parameter.toUrl())
                .retrieve()
                .body(String.class);

        // save
        return fileWriter.save(parameter, jsonResponse);
    }
}