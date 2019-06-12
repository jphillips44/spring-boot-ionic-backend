package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteNewDto;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.resources.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.resources.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " +id + "Tipo: " + Cliente.class.getName()));
		
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
		
		
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateObj(newObj, obj);
		return clienteRepository.save(newObj);
		
	}

	public void delete(Integer id) {
		find(id);
		try{
			clienteRepository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir pois há entidades relacionadas");
		}
		
    }
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String oderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), oderBy);
		return clienteRepository.findAll(pageRequest);
		
	}
	
	public Cliente fromDTO(ClienteDTO objtDto) {
		return new Cliente(objtDto.getId(), objtDto.getNome(), objtDto.getEmail(),null,null); 
		
	}
	
	public Cliente fromDTO(ClienteNewDto objtDto) {
		Cliente cli = new Cliente(null, objtDto.getNome(), objtDto.getEmail(),objtDto.getCpfOuCnpj(),TipoCliente.toEnum(objtDto.getTipo()));
		Optional<Cidade> cid = cidadeRepository.findById(objtDto.getCidadeId());
		Endereco end = new Endereco(null, objtDto.getLogadouro(), objtDto.getNumero(),objtDto.getComplemento(), objtDto.getBairro(), objtDto.getCep(), cli, cid.get());
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objtDto.getTelefone1());
		cli.getTelefones().add(objtDto.getTelefone1() != null ? objtDto.getTelefone2() : null);
		cli.getTelefones().add(objtDto.getTelefone1() != null ? objtDto.getTelefone3() : null);
		
		return cli; 		 
		
	}
	
	private void updateObj(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}
	
}
