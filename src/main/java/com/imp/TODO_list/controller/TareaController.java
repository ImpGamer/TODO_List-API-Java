package com.imp.TODO_list.controller;

import com.imp.TODO_list.Model.Tarea;
import com.imp.TODO_list.Service.impl.TareaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TareaController {
    @Autowired
    private TareaServiceImpl tareaService;
    @GetMapping
    public ResponseEntity<List<Tarea>> showTasks(@RequestParam("no_completes") boolean no_completes) {
        List<Tarea> tasks = tareaService.mostrarTareas(no_completes);
        return new ResponseEntity<>(tasks,null, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody Tarea tarea) {
        String message = tareaService.agregarTarea(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Optional<Long> id,@RequestParam(
            name = "allCompletes", defaultValue = "false") String allComplete) {
        boolean deleteCompletes = Boolean.parseBoolean(allComplete);
        if(deleteCompletes) {
            id = Optional.empty();
        }

        String message = tareaService.eliminarTarea(id,deleteCompletes);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editTask(@PathVariable Long id,@RequestBody Tarea tarea) {
        Tarea tarea1 = tareaService.editarTarea(id,tarea);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(tarea1);
    }
    @PatchMapping("/asComplete")
    public ResponseEntity<?> markTaskAsComplete(@RequestParam("task_id") Long task_id) {
        String message = tareaService.marcarTareaComoCompletada(task_id);
        return ResponseEntity.ok(message);
    }
}