package com.autobots.automanager;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Doc;
import com.autobots.automanager.entity.End;
import com.autobots.automanager.entity.Tel;
import com.autobots.automanager.repository.RepositorioCli;

@SpringBootApplication
public class AutomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Component
	public static class Runner implements ApplicationRunner {
		@Autowired
		public RepositorioCli repositorio;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Calendar calendario = Calendar.getInstance();
			calendario.set(2002, 05, 15);

			Client client = new Client();
			client.setNome("Kevin Romero Ribeiro");
			client.setDataCadastro(Calendar.getInstance().getTime());
			client.setDataNascimento(calendario.getTime());
			client.setNomeSocial("Dom Pedro");

			Tel tel = new Tel();
			tel.setDdd("21");
			tel.setNumero("981234576");
			client.getTels().add(tel);

			End end = new End();
			end.setEstado("Rio de Janeiro");
			end.setCidade("Rio de Janeiro");
			end.setBairro("Copacabana");
			end.setRua("Avenida AtlÃ¢ntica");
			end.setNumero("1702");
			end.setCodigoPostal("22021001");
			end.setInformacoesAdicionais("Hotel Copacabana palace");
			client.setEnd(end);

			Doc rg = new Doc();
			rg.setTipo("RG");
			rg.setNumero("1500");

			Doc cpf = new Doc();
			cpf.setTipo("RG");
			cpf.setNumero("00000000001");

			client.getDocs().add(rg);
			client.getDocs().add(cpf);

			repositorio.save(client);
		}
	}
}
