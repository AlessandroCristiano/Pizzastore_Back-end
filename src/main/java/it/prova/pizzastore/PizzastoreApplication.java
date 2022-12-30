package it.prova.pizzastore;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.ClienteService;
import it.prova.pizzastore.service.OrdineService;
import it.prova.pizzastore.service.PizzaService;
import it.prova.pizzastore.service.RuoloService;
import it.prova.pizzastore.service.UtenteService;

@SpringBootApplication
public class PizzastoreApplication implements CommandLineRunner{
	
	@Autowired
	private UtenteService utenteServiceInstance;

	@Autowired
	private RuoloService ruoloServiceInstance;
	
	@Autowired
	private ClienteService clienteServiceInstance;
	
	@Autowired
	private PizzaService pizzaServiceInstance;
	
	@Autowired
	private OrdineService ordineServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(PizzastoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ADMIN_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ADMIN_ROLE));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.PIZZAIOLO_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo", Ruolo.PIZZAIOLO_ROLE));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.PROPRIETARIO_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Proprietario", Ruolo.PROPRIETARIO_ROLE));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.FATTORINO_ROLE) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino", Ruolo.FATTORINO_ROLE));
		}
		
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", LocalDate.now());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ADMIN_ROLE));
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}
		
		if (utenteServiceInstance.findByUsername("pizzaiolo") == null) {
			Utente pizzaiolo = new Utente("pizzaiolo", "pizzaiolo", "Luca", "Esposito", LocalDate.now());
			pizzaiolo.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.PIZZAIOLO_ROLE));
			utenteServiceInstance.inserisciNuovo(pizzaiolo);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(pizzaiolo.getId());
		}
		
		if (utenteServiceInstance.findByUsername("proprietario") == null) {
			Utente proprietario = new Utente("proprietario", "proprietario", "Ciro", "Caiotta", LocalDate.now());
			proprietario.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.PROPRIETARIO_ROLE));
			utenteServiceInstance.inserisciNuovo(proprietario);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(proprietario.getId());
		}
		
		if (utenteServiceInstance.findByUsername("fattorino") == null) {
			Utente fattorino = new Utente("fattorino", "fattorino", "Alberto", "giacomini", LocalDate.now());
			fattorino.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.FATTORINO_ROLE));
			utenteServiceInstance.inserisciNuovo(fattorino);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(fattorino.getId());
		}
		
		Cliente cliente = new Cliente("luca", "belgianni", "via");
		clienteServiceInstance.inserisciNuovo(cliente);
		
		Pizza pizza = new Pizza("margherita", "pomodoro", 5);
		pizzaServiceInstance.inserisciNuovo(pizza);
		
		
		
	}

}
