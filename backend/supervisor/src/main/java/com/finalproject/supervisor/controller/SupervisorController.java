package com.finalproject.supervisor.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.supervisor.entities.SupervisorEntity;
import com.finalproject.supervisor.repository.supervisorRepository;
import com.finalproject.supervisor.response.Response;

@RestController
public class SupervisorController {
	@Autowired
	private supervisorRepository supervisorrepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/supervisor", method = RequestMethod.GET)
	public List<SupervisorEntity> Get() {
		return supervisorrepository.findAll();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/supervisor", method = RequestMethod.POST)
	public ResponseEntity<Response<SupervisorEntity>> Post(@Valid @RequestBody SupervisorEntity supervisor,
			BindingResult result) {
		Response<SupervisorEntity> response = new Response<SupervisorEntity>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		supervisorrepository.save(supervisor);
		response.setData(supervisor);
		return ResponseEntity.ok(response);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/supervisor/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response<SupervisorEntity>> Put(@PathVariable(value = "id") long id,
			@Valid @RequestBody SupervisorEntity newsupervisor, BindingResult result) {
		Optional<SupervisorEntity> oldSupervisor = supervisorrepository.findById(id);
		Response<SupervisorEntity> response = new Response<SupervisorEntity>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		if (oldSupervisor.isPresent()) {
			SupervisorEntity supervisor = oldSupervisor.get();
			supervisor.setNome(newsupervisor.getNome());
			supervisor.setAreaResponsavel(newsupervisor.getAreaResponsavel());
			supervisor.setSalario(newsupervisor.getSalario());

			response.setData(supervisor);
			supervisorrepository.save(supervisor);
			return ResponseEntity.ok(response);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/supervisor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<SupervisorEntity> supervisor = supervisorrepository.findById(id);
		if (supervisor.isPresent()) {
			supervisorrepository.delete(supervisor.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
