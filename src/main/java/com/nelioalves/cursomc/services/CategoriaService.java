package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.resources.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.resources.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRepo;

	public Categoria find(Integer id) {
		Optional<Categoria> objCategoria = catRepo.findById(id);
		
		return objCategoria.orElseThrow(() -> new ObjectNotFoundException(
				"Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return catRepo.save(obj);
		
		
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return catRepo.save(obj);
		
	}

	public void delete(Integer id) {
		find(id);
		try{
			catRepo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possuí produtos");
		}
		
    }
	
	public List<Categoria> findAll() {
		return catRepo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String oderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), oderBy);
		return catRepo.findAll(pageRequest);
		
	}
	
	public Categoria fromCategoriaDTO(CategoriaDTO objtDto) {
		return new Categoria(objtDto.getId(), objtDto.getNome()); 
		
	}
}