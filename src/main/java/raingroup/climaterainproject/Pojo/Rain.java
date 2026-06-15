package raingroup.climaterainproject.Pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Rain")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rain {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private double longitude;
    private double latitude;
    private Double value;
}
