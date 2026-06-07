package raingroup.climaterainproject.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import raingroup.climaterainproject.Dto.ClimateDataInputDTO;
import raingroup.climaterainproject.Dto.NasaResponse;
import raingroup.climaterainproject.IO.FileReader;
import raingroup.climaterainproject.IO.FileWriter;
import raingroup.climaterainproject.IO.Parameter;
import raingroup.climaterainproject.Pojo.ClimateData;
import raingroup.climaterainproject.Repository.ClimateData_Repository;

@Service
public class Rain_Service {

    private final RestClient restClient;
    private final FileWriter fileWriter;
    private final FileReader fileReader;
    private final ClimateData_Repository repository;
    private final ObjectMapper objectMapper;

    public Rain_Service(ClimateData_Repository repository) {
        this.restClient = RestClient.create("https://power.larc.nasa.gov");
        this.fileWriter = new raingroup.climaterainproject.IO.FileWriter();
        this.fileReader = new FileReader();
        this.repository = repository;
        this.objectMapper = new ObjectMapper();
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
        fileWriter.save(parameter, jsonResponse);
        return "Saved file: " + parameter.toFileName();
    }

    public String saveFile(String fileName) throws Exception {
        NasaResponse nasaResponse = fileReader.read(fileName);

        // CONVERT -> ClimateDataInputDTO (only the fields we want)
        ClimateDataInputDTO dto = nasaResponse.toInputDTO();

        // MAP DTO -> ClimateData Entity
        ClimateData row = new ClimateData();
        row.setStartdate(dto.getStartdate());
        row.setEnddate(dto.getEnddate());
        row.setLongitude(dto.getLongitude());
        row.setLatitude(dto.getLatitude());
        row.setMeasurements(dto.getMeasurements());
        row.setMeasurementInfo(dto.getMeasurementInfo());

        repository.save(row);

        return "Inserted into DB with id: " + row.getId();
    }
}