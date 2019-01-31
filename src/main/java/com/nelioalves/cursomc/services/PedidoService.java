package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.resources.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido find(Integer id) {
		
		Optional<Pedido> objPedido = pedidoRepository.findById(id);
		
		return objPedido.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " +id + "Tipo: " + Cliente.class.getName()));
		
	}
}
