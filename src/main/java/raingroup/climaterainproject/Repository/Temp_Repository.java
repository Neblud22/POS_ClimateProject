package raingroup.climaterainproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raingroup.climaterainproject.Pojo.Temperature;

public interface Temp_Repository extends JpaRepository<Temperature, Long> {
}
