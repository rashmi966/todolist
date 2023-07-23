package org.jt.todolist.controller;

import org.jt.todolist.model.Todo;
import org.jt.todolist.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    
    @RequestMapping("/todo")
       String todopage(Model model) {
        var todos = todoService.todos();
        model.addAttribute("todos", todos);
        return "todopage";
    }
    @PostMapping("/addtodo") 
    String addtodo( @RequestParam("action") String action) {
        System.out.println(action);

        var todo =  Todo.builder().action(action).build();
        todoService.addTodo(todo);

        return "redirect:/todo";

        
    }  
    @GetMapping("/removetodo")
    public String removeTodo(@RequestParam int id) {
        System.out.println(id);
        todoService.removeById(id);
        return "redirect:/todo";
        
    } 
    @GetMapping("/completetodo")
    public String iscompleted(@RequestParam int id , @RequestParam("checked") boolean isCompleted) {
        todoService.updateCompletedStatusById(id, isCompleted);
        return "redirect:/todo";
    }
}
