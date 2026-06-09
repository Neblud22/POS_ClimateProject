package raingroup.climaterainproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raingroup.climaterainproject.Pojo.Temperature;
import java.util.List;

@Repository
public interface Temp_Repository extends JpaRepository<Temperature, Long> {
    List<Temperature> findByDate(String date);
}
