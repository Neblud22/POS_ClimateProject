package raingroup.climaterainproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raingroup.climaterainproject.Pojo.ClimateData;

@Repository
public interface ClimateData_Repository extends JpaRepository<ClimateData, Long> {
}