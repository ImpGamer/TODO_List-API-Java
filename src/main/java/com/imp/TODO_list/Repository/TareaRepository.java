package com.imp.TODO_list.Repository;

import com.imp.TODO_list.Model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long> {
    @Query(value = "SELECT * FROM tareas WHERE completado = 0",nativeQuery = true)
    List<Tarea> findAllTaskNoComplete();
    @Query(value = "SELECT * FROM tareas WHERE completado = 1",nativeQuery = true)
    List<Tarea> findAllTaskComplete();
}