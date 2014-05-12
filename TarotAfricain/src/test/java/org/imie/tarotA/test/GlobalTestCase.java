package org.imie.tarotA.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.imie.tarotA.Client;
import org.imie.tarotA.IService;
import org.imie.tarotA.model.EtatPartie;
import org.imie.tarotA.model.Joueur;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author imie
 *
 */
public class GlobalTestCase {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// Arrange	
		IService service = mock(IService.class);
		Client client = new Client();
		client.setService(service);
		EtatPartie etatInit = new EtatPartie();
		Joueur joueur1 = new Joueur();
		joueur1.setNumero(1);
		joueur1.setNom("toto");
		Joueur joueur2 = new Joueur();
		joueur2.setNumero(2);
		joueur2.setNom("tata");
		etatInit.getJoueurs().add(joueur1);
		etatInit.getJoueurs().add(joueur2);
		when(service.getEtatPartie()).thenReturn(etatInit);
		
		// Act
		client.rejoindreParti("toto");
		client.rejoindreParti("tata");
		List<Joueur> result = client.listeJoueur();
		
		// Assert
		Assert.assertEquals(result.get(0).getNom(), "toto");
		Assert.assertEquals(result.get(0).getNumero(), Integer.valueOf(1));
		Assert.assertEquals(result.get(1).getNom(), "tata");
		Assert.assertEquals(result.get(1).getNumero(), Integer.valueOf(2));
		verify(service,times(2)).majEtat(any(EtatPartie.class));
		
	}

}
