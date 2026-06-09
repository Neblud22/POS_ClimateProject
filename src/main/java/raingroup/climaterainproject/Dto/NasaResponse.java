package raingroup.climaterainproject.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaResponse {

    private Geometry geometry;

    private Properties properties;

    @JsonProperty("parameters")
    private Map<String, Map<String, String>> parameters;

    private Header header;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Geometry {
        private List<Double> coordinates;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
        @JsonProperty("parameter")
        private Map<String, Map<String, Double>> parameter;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Header {
        private String start;
        private String end;
    }

    // CONVERT NasaResponse -> ClimateDataInputDTO (only the fields we want)
    public ClimateDataInputDTO toInputDTO() {
        ClimateDataInputDTO dto = new ClimateDataInputDTO();

        dto.setStartdate(this.header.getStart());
        dto.setEnddate(this.header.getEnd());
        dto.setLongitude(this.geometry.getCoordinates().get(0));
        dto.setLatitude(this.geometry.getCoordinates().get(1));
        dto.setMeasurements(this.properties.getParameter());
        dto.setMeasurementInfo(this.parameters);

        return dto;
    }
}
