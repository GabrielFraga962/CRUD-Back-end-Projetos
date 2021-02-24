package com.drogariasantaedwiges.SantaEdwiges.repository;

import java.util.List;

import com.drogariasantaedwiges.SantaEdwiges.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

	
	@Repository
	public interface ProdutoRepository extends JpaRepository<Produto, Long> {
		public List<Produto> findAllByDescricaoContainingIgnoreCase( String descricao);
		
}

