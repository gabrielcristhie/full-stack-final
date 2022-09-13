package com.finalproject.aparelhos.controller;

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

import com.finalproject.aparelhos.entities.AparelhoEntity;
import com.finalproject.aparelhos.repository.AparelhosRepository;
import com.finalproject.aparelhos.response.Response;

@RestController
public class AparelhoController {
	@Autowired
	private AparelhosRepository aparelhosRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/aparelho", method = RequestMethod.GET)
	public List<AparelhoEntity> Get() {
		return aparelhosRepository.findAll();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/aparelho", method = RequestMethod.POST)
	public ResponseEntity<Response<AparelhoEntity>> Post(@Valid @RequestBody AparelhoEntity aparelho,
			BindingResult result) {
		Response<AparelhoEntity> response = new Response<AparelhoEntity>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		aparelhosRepository.save(aparelho);
		response.setData(aparelho);
		return ResponseEntity.ok(response);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/aparelho/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response<AparelhoEntity>> Put(@PathVariable(value = "id") long id,
			@Valid @RequestBody AparelhoEntity newaparelho, BindingResult result) {
		Optional<AparelhoEntity> oldAparelho = aparelhosRepository.findById(id);
		Response<AparelhoEntity> response = new Response<AparelhoEntity>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		if (oldAparelho.isPresent()) {
			AparelhoEntity aparelho = oldAparelho.get();
			aparelho.setNomeAparelho(newaparelho.getNomeAparelho());
			aparelho.setNumeroDeSerie(newaparelho.getNumeroDeSerie());
			aparelho.setModelo(newaparelho.getModelo());
			aparelho.setMarca(newaparelho.getMarca());
			aparelho.setDataAquisicao(newaparelho.getDataAquisicao());
			aparelho.setPosse(newaparelho.getPosse());

			response.setData(aparelho);
			aparelhosRepository.save(aparelho);
			return ResponseEntity.ok(response);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/aparelho/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
		Optional<AparelhoEntity> aparelho = aparelhosRepository.findById(id);
		if (aparelho.isPresent()) {
			aparelhosRepository.delete(aparelho.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
