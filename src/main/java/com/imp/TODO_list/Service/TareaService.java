package com.imp.TODO_list.Service;

import com.imp.TODO_list.Model.Tarea;
import com.imp.TODO_list.exceptions.EmptyTasksException;
import com.imp.TODO_list.exceptions.NotValidTaskException;
import com.imp.TODO_list.exceptions.TaskNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TareaService {
    String agregarTarea(Tarea tarea)throws NotValidTaskException;
    String eliminarTarea(Optional<Long> id,boolean allComplete)throws TaskNotFoundException;
    List<Tarea> mostrarTareas(boolean no_complete)throws EmptyTasksException;
    String eliminarTareasCompletadas()throws EmptyTasksException;
    String marcarTareaComoCompletada(Long id)throws TaskNotFoundException;
    Tarea editarTarea(Long id,Tarea tarea)throws NotValidTaskException, TaskNotFoundException;
}
