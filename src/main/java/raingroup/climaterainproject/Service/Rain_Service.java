package raingroup.climaterainproject.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import raingroup.climaterainproject.Dto.ClimateDataInputDTO;
import raingroup.climaterainproject.Dto.NasaResponse;
import raingroup.climaterainproject.IO.FileReader;
import raingroup.climaterainproject.IO.FileWriter;
import raingroup.climaterainproject.IO.Parameter;
import raingroup.climaterainproject.Pojo.Humidity;
import raingroup.climaterainproject.Pojo.Rain;
import raingroup.climaterainproject.Pojo.Temperature;
import raingroup.climaterainproject.Pojo.Wind;
import raingroup.climaterainproject.Repository.Humidity_Repository;
import raingroup.climaterainproject.Repository.Rain_Repository;
import raingroup.climaterainproject.Repository.Temp_Repository;
import raingroup.climaterainproject.Repository.Wind_Repository;

import java.util.Map;

@Service
public class Rain_Service {

    private final RestClient restClient;
    private final FileWriter fileWriter;
    private final FileReader fileReader;
    private final Rain_Repository rainRepository;
    private final Temp_Repository temperatureRepository;
    private final Humidity_Repository humidityRepository;
    private final Wind_Repository windRepository;

    public Rain_Service(Rain_Repository rainRepository, Temp_Repository temperatureRepository, Humidity_Repository humidityRepository, Wind_Repository windRepository) {
        this.restClient = RestClient.create("https://power.larc.nasa.gov");
        this.fileWriter = new raingroup.climaterainproject.IO.FileWriter();
        this.fileReader = new FileReader();
        this.rainRepository = rainRepository;
        this.temperatureRepository = temperatureRepository;
        this.humidityRepository = humidityRepository;
        this.windRepository = windRepository;
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

        // lon & lat
        double lon = dto.getLongitude();
        double lat = dto.getLatitude();

        Map<String, Map<String, Double>> measurements = dto.getMeasurements();

        // MAP DTO -> ClimateData Entity
        if (measurements.containsKey("PRECTOTCORR")) {
            measurements.get("PRECTOTCORR").forEach((date, value) -> {
                Rain row = Rain.builder()
                        .date(date)
                        .longitude(lon)
                        .latitude(lat)
                        .value(value)
                        .build();
                rainRepository.save(row);
            });
        }

        if (measurements.containsKey("T2M")) {
            measurements.get("T2M").forEach((date, value) -> {
                Temperature row = Temperature.builder()
                        .date(date)
                        .longitude(lon)
                        .latitude(lat)
                        .value(value)
                        .build();
                temperatureRepository.save(row);
            });
        }

        if (measurements.containsKey("RH2M")) {
            measurements.get("RH2M").forEach((date, value) -> {
                Humidity row = Humidity.builder()
                        .date(date)
                        .longitude(lon)
                        .latitude(lat)
                        .value(value)
                        .build();
                humidityRepository.save(row);
            });
        }

        if (measurements.containsKey("WS2M")) {
            measurements.get("WS2M").forEach((date, value) -> {
                Wind row = Wind.builder()
                        .date(date)
                        .longitude(lon)
                        .latitude(lat)
                        .value(value)
                        .build();
                windRepository.save(row);
            });
        }

        return "Inserted " + measurements.get("PRECTOTCORR").size() + " days into all tables";
    }
}