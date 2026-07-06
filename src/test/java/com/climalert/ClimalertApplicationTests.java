package com.climalert;

import com.climalert.models.entities.clima.Clima;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClimalertApplicationTests {

	private Clima climaCon(double temp, int humedad) {
		Clima c = new Clima();
		c.setUbicacion("Buenos Aires");
		c.setTemperaturaCelsius(temp);
		c.setHumedad(humedad);
		return c;
	}

	@Test
	void temperaturaYHumedadAltas_generanAlerta() {
		assertTrue(climaCon(36.0, 61).esCondicionDeAlerta());
	}

	@Test
	void soloTemperaturaAlta_noGeneraAlerta() {
		assertFalse(climaCon(40.0, 50).esCondicionDeAlerta());
	}

	@Test
	void soloHumedadAlta_noGeneraAlerta() {
		assertFalse(climaCon(30.0, 80).esCondicionDeAlerta());
	}

	@Test
	void enElLimite_noGeneraAlerta() {
		// 35 y 60 exactos no alcanzan: la condicion es estrictamente mayor.
		assertFalse(climaCon(35.0, 60).esCondicionDeAlerta());
	}
}
