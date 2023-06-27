package com.autobots.automanager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.autobots.automanager.entity.CredencialUsuarioSenha;
import com.autobots.automanager.entity.Doc;
import com.autobots.automanager.entity.Email;
import com.autobots.automanager.entity.Emp;
import com.autobots.automanager.entity.End;
import com.autobots.automanager.entity.Mercadoria;
import com.autobots.automanager.entity.Service;
import com.autobots.automanager.entity.Tel;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Veiculo;
import com.autobots.automanager.entity.Venda;
import com.autobots.automanager.enums.PerfilUsuario;
import com.autobots.automanager.enums.TipoDocumento;
import com.autobots.automanager.enums.TipoVeiculo;
import com.autobots.automanager.repository.RepositorioEmpresa;

@SpringBootApplication
public class AutomanagerApplication implements CommandLineRunner {

	@Autowired
	private RepositorioEmpresa repositorioEmpresa;

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Emp emp = new Emp();
		emp.setRazaoSocial("Car service toyota ltda");
		emp.setNomeFantasia("Car service manutenção veicular");
		emp.setCadastro(new Date());

		End endEmpresa = new End();
		endEmpresa.setEstado("São Paulo");
		endEmpresa.setCidade("São Paulo");
		endEmpresa.setBairro("Centro");
		endEmpresa.setRua("Av. São João");
		endEmpresa.setNumero("00");
		endEmpresa.setCodigoPostal("01035-000");

		emp.setEnd(endEmpresa);

		Tel telEmpresa = new Tel();
		telEmpresa.setDdd("011");
		telEmpresa.setNumero("986454527");

		emp.getTels().add(telEmpresa);

		User funcionario = new User();
		funcionario.setNome("Kevin Romero Ribeiro");
		funcionario.setNomeSocial("Dom Pedro");
		funcionario.getPerfis().add(PerfilUsuario.FUNCIONARIO);

		Email emailFuncionario = new Email();
		emailFuncionario.setEndereco("a@a.com");

		funcionario.getEmails().add(emailFuncionario);

		End endFuncionario = new End();
		endFuncionario.setEstado("São Paulo");
		endFuncionario.setCidade("São Paulo");
		endFuncionario.setBairro("Jardins");
		endFuncionario.setRua("Av. São Gabriel");
		endFuncionario.setNumero("00");
		endFuncionario.setCodigoPostal("01435-001");

		funcionario.setEnd(endFuncionario);

		emp.getUsers().add(funcionario);

		Tel telFuncionario = new Tel();
		telFuncionario.setDdd("011");
		telFuncionario.setNumero("9854633728");

		funcionario.getTels().add(telFuncionario);

		Doc cpf = new Doc();
		cpf.setDataEmissao(new Date());
		cpf.setNumero("856473819229");
		cpf.setTipo(TipoDocumento.CPF);

		funcionario.getDocs().add(cpf);

		CredencialUsuarioSenha credencialFuncionario = new CredencialUsuarioSenha();
		credencialFuncionario.setInativo(false);
		credencialFuncionario.setNomeUsuario("dompedrofuncionario");
		credencialFuncionario.setSenha("123456");
		credencialFuncionario.setCriacao(new Date());
		credencialFuncionario.setUltimoAcesso(new Date());

		funcionario.getCredenciais().add(credencialFuncionario);

		User fornecedor = new User();
		fornecedor.setNome("Componentes varejo de partes automotivas ltda");
		fornecedor.setNomeSocial("Loja do carro, vendas de componentes automotivos");
		fornecedor.getPerfis().add(PerfilUsuario.FORNECEDOR);

		Email emailFornecedor = new Email();
		emailFornecedor.setEndereco("f@f.com");

		fornecedor.getEmails().add(emailFornecedor);

		CredencialUsuarioSenha credencialFornecedor = new CredencialUsuarioSenha();
		credencialFornecedor.setInativo(false);
		credencialFornecedor.setNomeUsuario("dompedrofornecedor");
		credencialFornecedor.setSenha("123456");
		credencialFornecedor.setCriacao(new Date());
		credencialFornecedor.setUltimoAcesso(new Date());

		fornecedor.getCredenciais().add(credencialFornecedor);

		Doc cnpj = new Doc();
		cnpj.setDataEmissao(new Date());
		cnpj.setNumero("00014556000100");
		cnpj.setTipo(TipoDocumento.CNPJ);

		fornecedor.getDocs().add(cnpj);

		End endFornecedor = new End();
		endFornecedor.setEstado("Rio de Janeiro");
		endFornecedor.setCidade("Rio de Janeiro");
		endFornecedor.setBairro("Centro");
		endFornecedor.setRua("Av. República do chile");
		endFornecedor.setNumero("00");
		endFornecedor.setCodigoPostal("20031-170");

		fornecedor.setEnd(endFornecedor);

		emp.getUsers().add(fornecedor);
		
		Mercadoria rodaLigaLeve = new Mercadoria();
		rodaLigaLeve.setCadastro(new Date());
		rodaLigaLeve.setFabricacao(new Date());
		rodaLigaLeve.setNome("Roda de liga leva modelo toyota etios");
		rodaLigaLeve.setValidade(new Date());
		rodaLigaLeve.setQuantidade(30);
		rodaLigaLeve.setValor(300.0);
		rodaLigaLeve.setDescricao("Roda de liga leve original de fábrica da toyta para modelos do tipo hatch");

		emp.getMercadorias().add(rodaLigaLeve);

		fornecedor.getMercadorias().add(rodaLigaLeve);

		User cliente = new User();
		cliente.setNome("Pedro Alcântara de Bragança e Bourbon");
		cliente.setNomeSocial("Dom pedro cliente");
		cliente.getPerfis().add(PerfilUsuario.CLIENTE);

		Email emailCliente = new Email();
		emailCliente.setEndereco("c@c.com");

		cliente.getEmails().add(emailCliente);

		Doc cpfCliente = new Doc();
		cpfCliente.setDataEmissao(new Date());
		cpfCliente.setNumero("12584698533");
		cpfCliente.setTipo(TipoDocumento.CPF);

		cliente.getDocs().add(cpfCliente);

		CredencialUsuarioSenha credencialCliente = new CredencialUsuarioSenha();
		credencialCliente.setInativo(false);
		credencialCliente.setNomeUsuario("dompedrocliente");
		credencialCliente.setSenha("123456");
		credencialCliente.setCriacao(new Date());
		credencialCliente.setUltimoAcesso(new Date());

		cliente.getCredenciais().add(credencialCliente);

		End endCliente = new End();
		endCliente.setEstado("São Paulo");
		endCliente.setCidade("São José dos Campos");
		endCliente.setBairro("Centro");
		endCliente.setRua("Av. Dr. Nelson D'Ávila");
		endCliente.setNumero("00");
		endCliente.setCodigoPostal("12245-070");

		cliente.setEnd(endCliente);
		
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca("ABC-0000");
		veiculo.setModelo("corolla-cross");
		veiculo.setTipo(TipoVeiculo.SUV);
		veiculo.setProprietario(cliente);
		
		cliente.getVeiculos().add(veiculo);
		
		emp.getUsers().add(cliente);

		Service trocaRodas = new Service();
		trocaRodas.setDescricao("Troca das rodas do carro por novas");
		trocaRodas.setNome("Troca de rodas");
		trocaRodas.setValor(50);

		Service alinhamento = new Service();
		alinhamento.setDescricao("Alinhamento das rodas do carro");
		alinhamento.setNome("Alinhamento de rodas");
		alinhamento.setValor(50);

		emp.getServices().add(trocaRodas);
		emp.getServices().add(alinhamento);

		Venda venda = new Venda();
		venda.setCadastro(new Date());
		venda.setCliente(cliente);
		venda.getMercadorias().add(rodaLigaLeve);
		venda.setIdentificacao("1234698745");
		venda.setFuncionario(funcionario);
		venda.getServices().add(trocaRodas);
		venda.getServices().add(alinhamento);
		venda.setVeiculo(veiculo);
		veiculo.getVendas().add(venda);

		emp.getVendas().add(venda);

		repositorioEmpresa.save(emp);
		
		Mercadoria rodaLigaLeve2 = new Mercadoria();
		rodaLigaLeve2.setCadastro(new Date());
		rodaLigaLeve2.setFabricacao(new Date());
		rodaLigaLeve2.setNome("Roda de liga leva modelo toyota etios");
		rodaLigaLeve2.setValidade(new Date());
		rodaLigaLeve2.setQuantidade(30);
		rodaLigaLeve2.setValor(300.0);
		rodaLigaLeve2.setDescricao("Roda de liga leve original de fábrica da toyta para modelos do tipo hatch");
		
		Service alinhamento2 = new Service();
		alinhamento2.setDescricao("Alinhamento das rodas do carro");
		alinhamento2.setNome("Alinhamento de rodas");
		alinhamento2.setValor(50);
		
		Service balanceamento = new Service();
		balanceamento.setDescricao("balanceamento das rodas do carro");
		balanceamento.setNome("balanceamento de rodas");
		balanceamento.setValor(30);
		
		Venda venda2 = new Venda();
		venda2.setCadastro(new Date());
		venda2.setCliente(cliente);
		venda2.getMercadorias().add(rodaLigaLeve2);
		venda2.setIdentificacao("1234698749");
		venda2.setFuncionario(funcionario);
		venda2.getServices().add(balanceamento);
		venda2.getServices().add(alinhamento2);
		venda2.setVeiculo(veiculo);
		veiculo.getVendas().add(venda2);

		emp.getVendas().add(venda2);
		
		repositorioEmpresa.save(emp);

	}
}