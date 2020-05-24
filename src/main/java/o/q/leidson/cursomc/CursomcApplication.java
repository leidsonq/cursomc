package o.q.leidson.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import o.q.leidson.cursomc.domain.Categoria;
import o.q.leidson.cursomc.domain.Cidade;
import o.q.leidson.cursomc.domain.Cliente;
import o.q.leidson.cursomc.domain.Endereco;
import o.q.leidson.cursomc.domain.Estado;
import o.q.leidson.cursomc.domain.Produto;
import o.q.leidson.cursomc.domain.enums.TipoCliente;
import o.q.leidson.cursomc.repositories.CategoriaRepository;
import o.q.leidson.cursomc.repositories.CidadeRepository;
import o.q.leidson.cursomc.repositories.ClienteRepository;
import o.q.leidson.cursomc.repositories.EnderecoRepository;
import o.q.leidson.cursomc.repositories.EstadoRepository;
import o.q.leidson.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "088757373755", TipoCliente.PESSOAFISCA);
		cli1.getTelefones().addAll(Arrays.asList("99967785","67899898"));
		
		Endereco e1 = new Endereco (null, "Rua Dep. Sinval Bambirra", "296A", "Casa", "CDI2", "35701-379", cli1, c1);
		Endereco e2 = new Endereco (null, "Bambirra", "555", "Casa", "CDI", "35701-434", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}
