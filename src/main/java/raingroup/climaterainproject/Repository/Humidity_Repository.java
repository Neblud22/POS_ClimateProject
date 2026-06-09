package raingroup.climaterainproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raingroup.climaterainproject.Pojo.Humidity;

import java.util.List;

@Repository
public interface Humidity_Repository extends JpaRepository<Humidity,Long> {
    List<Humidity> findByDate(String date);

}
