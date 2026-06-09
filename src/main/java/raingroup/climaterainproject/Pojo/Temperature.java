package raingroup.climaterainproject.Pojo;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Temp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {
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

    @Column(name = "measurement", nullable = false)
    private Double measurements;
}
