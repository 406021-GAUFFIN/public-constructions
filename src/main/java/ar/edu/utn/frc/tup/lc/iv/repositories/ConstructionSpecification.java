package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * ConstructionSpecification is a utility class that provides specifications
 * for querying the {@link ConstructionEntity} based on various criteria.
 */
public class ConstructionSpecification {

    /**
     * Method to filter by construction status array.
     * @param  statuses the construction statuses to filter
     *
     * @return a specification with filter applied
     */
    public static Specification<ConstructionEntity> inStatus(List<ConstructionStatus> statuses) {
        return (root, query, criteriaBuilder) -> {
            if (statuses == null || statuses.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get("constructionStatus").in(statuses);
        };
    }
}
