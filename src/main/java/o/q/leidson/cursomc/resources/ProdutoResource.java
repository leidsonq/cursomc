package o.q.leidson.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import o.q.leidson.cursomc.domain.Produto;
import o.q.leidson.cursomc.dto.ProdutoDTO;
import o.q.leidson.cursomc.resources.util.URL;
import o.q.leidson.cursomc.services.ProdutoService;

@RestController
@RequestMapping (value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	ProdutoService service;

	
	@RequestMapping (value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam (value ="page", defaultValue = "0") Integer page,
			@RequestParam (value ="nome", defaultValue = "") String nome,
			@RequestParam (value ="categorias", defaultValue = "")String categorias,
			@RequestParam (value ="linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam (value ="orderBy", defaultValue = "nome")String orderBy,
			@RequestParam (value ="direction", defaultValue = "ASC")String direction) {
		List<Integer> ids = URL.decodeIntList(categorias);
		String nomeDecode = URL.decodeParam(nome);
		Page<Produto> list = service.search(nomeDecode, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}
	
	
}
