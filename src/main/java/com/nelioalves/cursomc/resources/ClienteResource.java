package com.nelioalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.ClienteServico;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteServico clienteService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET )
	public ResponseEntity<?> find(@PathVariable Integer id){
		
		Cliente objCli = clienteService.find(id) ;
		
		return ResponseEntity.ok(objCli);
	}

}
