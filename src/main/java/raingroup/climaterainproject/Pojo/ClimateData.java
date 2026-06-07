package raingroup.climaterainproject.Pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClimateData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startdate", nullable = false)
    private String startdate;

    @Column(name = "enddate", nullable = false)
    private String enddate;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "measurements", columnDefinition = "jsonb")
    private Map<String, Map<String, Double>> measurements;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "measurement_info", columnDefinition = "jsonb")
    private Map<String, Map<String, String>> measurementInfo;
}
