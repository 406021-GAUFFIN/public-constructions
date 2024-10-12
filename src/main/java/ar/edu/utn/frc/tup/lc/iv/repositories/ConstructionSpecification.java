package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * ConstructionSpecification is a utility class that provides specifications
 * for querying the {@link ConstructionEntity} based on various criteria.
 *
 * <p>This class is designed to work with Spring Data JPA's {@link Specification}
 * interface to dynamically build queries. Currently, it provides a method to filter
 * construction projects by their status.</p>
 */

public class ConstructionSpecification {

    /**
     * Creates a specification to filter {@link ConstructionEntity} instances
     * by their status.
     *
     * <p>This method returns a specification that can be used in a JPA query to
     * find all construction entities whose status matches any of the provided
     * statuses. If the list of statuses is null or empty, the specification
     * will match all construction entities (no filtering applied).</p>
     *
     * @param statuses the list of {@link ConstructionStatus} to filter by.
     *                 If the list is null or empty, no filtering is applied.
     * @return a {@link Specification} for filtering construction entities
     *         based on their status.
     */

    public static Specification<ConstructionEntity> hasStatusIn(List<ConstructionStatus> statuses) {
        return (root, query, criteriaBuilder) -> {
            if (statuses == null || statuses.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get("constructionStatus").in(statuses);
        };
    }
}
