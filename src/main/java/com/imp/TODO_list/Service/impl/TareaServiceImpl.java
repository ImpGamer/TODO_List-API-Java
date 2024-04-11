package com.imp.TODO_list.Service.impl;

import com.imp.TODO_list.Model.Tarea;
import com.imp.TODO_list.Repository.TareaRepository;
import com.imp.TODO_list.Service.TareaService;
import com.imp.TODO_list.exceptions.EmptyTasksException;
import com.imp.TODO_list.exceptions.NotValidTaskException;
import com.imp.TODO_list.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public String agregarTarea(Tarea tarea) throws NotValidTaskException {
        if(tarea.getTitulo().isEmpty()) {
            throw new NotValidTaskException("El titulo de la tarea no puede estar vacia");
        }
        tareaRepository.save(tarea);
        return "La tarea a sido almacenada correctamente";
    }

    @Override
    public String eliminarTarea(Optional<Long> id,boolean allComplete)throws TaskNotFoundException {
        String message = "";

        if(allComplete && id.isEmpty()) {
            message = eliminarTareasCompletadas();
        } else if(id.isPresent() && !allComplete) {
            Optional<Tarea> tareaBBDD = tareaRepository.findById(id.get());
            if(tareaBBDD.isEmpty()) {
                throw new TaskNotFoundException("La tarea con el id: "+id+" no fue encontrado");
            }
            tareaRepository.delete(tareaBBDD.get());
            message = "La tarea a sido eliminada correctamente";
        }
        return message;
    }

    @Override
    public List<Tarea> mostrarTareas(boolean no_complete) throws EmptyTasksException {
       List<Tarea> tareasAlm;
       if(no_complete) {
           tareasAlm = tareaRepository.findAllTaskNoComplete();
           if(tareasAlm.isEmpty()) {
               throw new EmptyTasksException("Hurra! No se encuentran tareas sin completar");
           }
       } else {
           tareasAlm = tareaRepository.findAll();
           if(tareasAlm.isEmpty()) {
               throw new EmptyTasksException("No se encuentran tareas registradas");
           }
       }

       return tareasAlm;
    }

    @Override
    public String eliminarTareasCompletadas() throws EmptyTasksException {
        List<Tarea> tareasCompletadas = tareaRepository.findAllTaskComplete();
        if(tareasCompletadas.isEmpty()) {
            throw new EmptyTasksException("No hay tareas completadas por eliminar");
        }
        tareaRepository.deleteAll(tareasCompletadas);
        return "Las tareas completadas han sido eliminadas";
    }

    @Override
    public String marcarTareaComoCompletada(Long id) throws TaskNotFoundException {
        Optional<Tarea> tareaBBDD = tareaRepository.findById(id);
        if(tareaBBDD.isEmpty()) {
            throw new TaskNotFoundException("La tarea con el id: "+id+" no fue encontrada");
        }
        Tarea tarea_editada = tareaBBDD.get();
        tarea_editada.setTitulo(tarea_editada.getTitulo()+" COMPLETADO!");
        tarea_editada.setCompletado(true);
        tareaRepository.save(tarea_editada);
        return "La tarea '"+tareaBBDD.get().getTitulo()+"' a sido marcada como completada!";
    }

    @Override
    public Tarea editarTarea(Long id, Tarea tarea) throws NotValidTaskException, TaskNotFoundException {
        Optional<Tarea> tareaBBDD = tareaRepository.findById(id);
        if(tareaBBDD.isEmpty()) {
            throw new TaskNotFoundException("La tarea con el id: "+id+" no fue encontrada D:");
        }
        if(tarea.getTitulo().isEmpty()) {
            throw new NotValidTaskException("El titulo de la tarea no puede estar vacia");
        }
        Tarea tarea_edit = tareaBBDD.get();
        tarea_edit.setTitulo(tarea.getTitulo());
        tarea_edit.setDescripcion(tarea.getDescripcion());

        return tareaRepository.save(tarea_edit);
    }
}