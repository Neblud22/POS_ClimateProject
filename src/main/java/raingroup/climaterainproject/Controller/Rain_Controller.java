package raingroup.climaterainproject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import raingroup.climaterainproject.Service.Rain_Service;

@RestController
@RequestMapping("/api/v1/rain")
public class Rain_Controller {
    private final Rain_Service service;

    public Rain_Controller(Rain_Service service) {
        this.service = service;
    }

    // GET /api/v1/rain/fetch?start=20250101&end=20250107&params=PRECTOTCORR,RH2M,T2M&longitude=15.44&latitude=47.38
    @GetMapping("/fetch")
    public String fetch(@RequestParam String start, @RequestParam String end, @RequestParam(defaultValue = "PRECTOTCORR,RH2M,T2M") String params, @RequestParam(defaultValue = "15.44") double longitude, @RequestParam(defaultValue = "47.38") double latitude) throws Exception {
        return service.fetchAndSave(start, end, params, longitude, latitude);
    }
}