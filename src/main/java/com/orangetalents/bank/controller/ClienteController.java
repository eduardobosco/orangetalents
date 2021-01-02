package com.orangetalents.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.bank.entities.Cliente;
import com.orangetalents.bank.exception.ClienteNotFoundException;
import com.orangetalents.bank.exception.ParametroObrigatorioException;
import com.orangetalents.bank.service.ClienteService;

@RequestMapping("/cliente")
@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody Cliente cliente) throws DataIntegrityViolationException {
		Cliente cli = new Cliente(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(),
				cliente.getDataNascimento());
		clienteService.inserir(cli);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		return new ResponseEntity<>(clienteService.listar(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable Long id) throws ClienteNotFoundException {
		Cliente cliente = clienteService.listarPorId(id);

		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> substituir(@PathVariable Long id, @RequestBody(required = true) Cliente cliente)
			throws ClienteNotFoundException, ParametroObrigatorioException {
		clienteService.substituir(id, cliente);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) throws ClienteNotFoundException {
		clienteService.deletar(id);
	}

}
