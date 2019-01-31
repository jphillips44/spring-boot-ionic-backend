package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.resources.exceptions.ObjectNotFoundException;

@Service
public class ClienteServico {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {
		
		Optional<Cliente> objCliente = clienteRepository.findById(id);
		
		return objCliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " +id + "Tipo: " + Cliente.class.getName()));
		
	}
}
