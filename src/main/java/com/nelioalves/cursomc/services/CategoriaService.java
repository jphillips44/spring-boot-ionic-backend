package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRepo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> objCategoria = catRepo.findById(id);
			
		return objCategoria.orElse(null);
		
	}
}
