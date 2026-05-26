package raingroup.climaterainproject.IO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Parameter {
    private String start;
    private String end;
    private String params;
    private double longitude;
    private double latitude;

    //url with params
    public String toUrl() {
        return "https://power.larc.nasa.gov/api/temporal/daily/point" +
                "?parameters=" + params +
                "&community=AG" +
                "&longitude=" + longitude +
                "&latitude=" + latitude +
                "&start=" + start +
                "&end=" + end +
                "&format=JSON";
    }

    // filename with params
    public String toFileName() {
        return "output/rain_" + start + "_" + end
                + "_lat" + latitude + "_lon" + longitude + ".json";
    }
}