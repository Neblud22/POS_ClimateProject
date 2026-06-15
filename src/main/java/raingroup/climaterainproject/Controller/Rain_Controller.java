package raingroup.climaterainproject.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import raingroup.climaterainproject.Dto.DailyDataDto;
import raingroup.climaterainproject.Pojo.Humidity;
import raingroup.climaterainproject.Pojo.Rain;
import raingroup.climaterainproject.Pojo.Temperature;
import raingroup.climaterainproject.Pojo.Wind;
import raingroup.climaterainproject.Repository.Humidity_Repository;
import raingroup.climaterainproject.Repository.Rain_Repository;
import raingroup.climaterainproject.Repository.Temp_Repository;
import raingroup.climaterainproject.Repository.Wind_Repository;
import raingroup.climaterainproject.Service.Rain_Service;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rain")
public class Rain_Controller {

    private final Rain_Service service;
    private final Rain_Repository rainRepository;
    private final Temp_Repository temperatureRepository;
    private final Humidity_Repository humidityRepository;
    private final Wind_Repository windRepository;

    public Rain_Controller(Rain_Service service, Rain_Repository rainRepository, Temp_Repository temperatureRepository, Humidity_Repository humidityRepository, Wind_Repository windRepository) {
        this.service = service;
        this.rainRepository = rainRepository;
        this.temperatureRepository = temperatureRepository;
        this.humidityRepository = humidityRepository;
        this.windRepository = windRepository;
    }

    // from nasa to output folder saven
    // GET /api/v1/rain/fetch?start=20250101&end=20250107&params=PRECTOTCORR,RH2M,T2M&longitude=15.44&latitude=47.38
    @GetMapping("/fetch")
    public String fetch(@RequestParam String start, @RequestParam String end, @RequestParam(defaultValue = "PRECTOTCORR,RH2M,T2M,WS2M") String params, @RequestParam(defaultValue = "15.44") double longitude, @RequestParam(defaultValue = "47.38") double latitude) throws Exception {
        return service.fetchAndSave(start, end, params, longitude, latitude);
    }

    // von file to db readen
    @GetMapping("/savetodb")
    public String saveToDb(@RequestParam String fileName) throws Exception {
        return service.saveFile(fileName);
    }

    // endpoints für GET pro Tag pro parameter
    // GET /api/v1/rain/data/rain?date=20250105
    @GetMapping("/rain")
    public ResponseEntity<List<Rain>> getRain(@RequestParam String date) {
        return ResponseEntity.ok(rainRepository.findByDate(date));
    }

    // GET /api/v1/rain/data/temperature?date=20250105
    @GetMapping("/temperature")
    public ResponseEntity<List<Temperature>> getTemperature(@RequestParam String date) {
        return ResponseEntity.ok(temperatureRepository.findByDate(date));
    }

    // GET /api/v1/rain/data/humidity?date=20250105
    @GetMapping("/humidity")
    public ResponseEntity<List<Humidity>> getHumidity(@RequestParam String date) {
        return ResponseEntity.ok(humidityRepository.findByDate(date));
    }

    // GET /api/v1/rain/data/wind?date=20250105
    @GetMapping("/wind")
    public ResponseEntity<List<Wind>> getWind(@RequestParam String date) {
        return ResponseEntity.ok(windRepository.findByDate(date));
    }

    // per day all params
    @GetMapping("/day")
    public ResponseEntity<DailyDataDto> getDailyData(@RequestParam String date) {

        DailyDataDto data = service.getDailyData(date);

        if (data.getRain() == null && data.getTemperature() == null && data.getHumidity() == null && data.getWind() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data);
    }
}