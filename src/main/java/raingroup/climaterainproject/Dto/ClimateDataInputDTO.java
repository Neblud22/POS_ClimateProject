package raingroup.climaterainproject.Dto;

import lombok.Data;

import java.util.Map;

@Data
public class ClimateDataInputDTO {
    private String startdate;
    private String enddate;
    private double longitude;
    private double latitude;

    // PRECTOTCORR: "20250101": , "RH2M":, "T2M":
    private Map<String, Map<String, Double>> measurements;

    // PRECTOTCORR: { "units": "mm/day", "longname": "Precipitation Corrected"
    private Map<String, Map<String, String>> measurementInfo;
}
