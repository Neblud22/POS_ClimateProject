package raingroup.climaterainproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raingroup.climaterainproject.Pojo.Rain;

import java.util.List;

@Repository
public interface Rain_Repository extends JpaRepository<Rain, Long> {
    List<Rain> findByDate(String date);

}