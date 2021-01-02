package com.orangetalents.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.orangetalents.bank.entities.Cliente;
import com.orangetalents.bank.exception.ClienteNotFoundException;
import com.orangetalents.bank.exception.ParametroObrigatorioException;
import com.orangetalents.bank.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	
	public Cliente inserir(Cliente cliente) throws DataIntegrityViolationException {
		try {
		Cliente clienteSalvoNoBd = clienteRepository.save(cliente);
		return clienteSalvoNoBd;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("CPF ou e-mail de Cliente Já cadastrados");
		}
	}
	
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	public Cliente listarPorId(Long id) throws ClienteNotFoundException {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		
		if(optionalCliente.isPresent()) {
			return optionalCliente.get();
		}
		throw new ClienteNotFoundException("Cliente com id " + id + " não encontrada");
	}
	
	public Cliente substituir(Long id, Cliente cliente) throws ParametroObrigatorioException, ClienteNotFoundException {
		if(cliente == null) throw new ParametroObrigatorioException("Campo 'cliente' é obrigatório");
		
		Cliente clienteNoBanco = listarPorId(id);
		
		if(cliente.getNome() != null) {
			clienteNoBanco.setNome(cliente.getNome());
		}
		
		if(cliente.getCpf() != null) {
			clienteNoBanco.setCpf(cliente.getCpf());
		}
		
		if(cliente.getEmail() != null) {
			clienteNoBanco.setEmail(cliente.getEmail());
		}
		
		if(cliente.getDataNascimento() != null) {
			clienteNoBanco.setDataNascimento(cliente.getDataNascimento());
		}
		
		return clienteRepository.save(clienteNoBanco);
	}
	
	public void deletar(Long id) throws ClienteNotFoundException {
		Cliente clienteNoBanco = listarPorId(id);
		clienteRepository.delete(clienteNoBanco);
	}

}
