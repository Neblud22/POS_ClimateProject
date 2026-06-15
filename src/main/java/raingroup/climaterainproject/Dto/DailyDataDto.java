package raingroup.climaterainproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyDataDto {
    private String date;
    private Double rain;
    private Double temperature;
    private Double humidity;
    private Double wind;
}
