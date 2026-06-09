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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private double longitude;
    private double latitude;
    private Double value;
}
