package raingroup.climaterainproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raingroup.climaterainproject.Pojo.Wind;
import java.util.List;

@Repository
public interface Wind_Repository extends JpaRepository <Wind, Long> {
    List<Wind> findByDate(String date);

}
