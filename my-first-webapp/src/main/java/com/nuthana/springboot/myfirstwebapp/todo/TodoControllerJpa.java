package com.nuthana.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

//	private TodoService todoService;

	private TodoRepository todoRepository;

	public TodoControllerJpa(TodoRepository todoRepository) { // (TodoService todoService,
		super();
//		this.todoService = todoService;
		this.todoRepository = todoRepository;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUsername(model);

		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);

		return "listTodos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	// like if we have more data coming form like description then we need to bind
	// all of that using @RequestParam so we bind them to Todo Bean(Command Bean)
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) { // (@RequestParam String
																							// description,here we are
		// using
		// Todo todo bean is the form backing object and also use this form backing
		// object in jsp aswell
		// for binding instead of before //main thing is 1st parameter
		// should modelMap model

		if (result.hasErrors()) {
			return "todo";
		}

		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
		// todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(),
		// todo.isDone());
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		// Delete todo

		todoRepository.deleteById(id);
		return "redirect:list-todos";

	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
//		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}

	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
