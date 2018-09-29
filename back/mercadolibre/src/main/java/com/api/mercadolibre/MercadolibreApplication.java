package com.api.mercadolibre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mercadolibre.sdk.AuthorizationFailure;
import com.mercadolibre.sdk.Meli;

@SpringBootApplication
public class MercadolibreApplication {

	public static void main(String[] args) throws AuthorizationFailure {
		SpringApplication.run(MercadolibreApplication.class, args);
	}
}
