package fu.talenthub.job.repository;

import fu.talenthub.job.entity.Job;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    @Query("""
    SELECT j
    FROM Job j
    LEFT JOIN FETCH j.department
""")
    List<Job> findAllWithDepartment();

    @EntityGraph(attributePaths = {"department"})
    @Query("SELECT j FROM Job j")
    List<Job> findAllWithEntityGraph();
}
