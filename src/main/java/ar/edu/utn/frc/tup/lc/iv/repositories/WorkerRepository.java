package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {

    Optional<WorkerEntity> findWorkerEntityByDocument(String document);

    Optional<WorkerEntity> findWorkerEntityByCuil(String cuil);

    List<WorkerEntity> findWorkerEntityByCuilContaining(String document);

    List<WorkerEntity> findWorkerEntityByDocumentContaining(String cuil);


}
