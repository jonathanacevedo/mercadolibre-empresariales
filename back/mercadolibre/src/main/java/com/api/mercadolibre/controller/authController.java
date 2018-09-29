package com.api.mercadolibre.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.sdk.AuthorizationFailure;
import com.mercadolibre.sdk.Meli;
import com.mercadolibre.sdk.MeliException;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.Response;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders="*", maxAge = 3600)
@RestController
public class authController {
	
	private Meli m = new Meli(2312045817622346L, "4JwhUyMi4KUm5XWwNny2IZVslQ5FTJTR");

	@GetMapping(value = "/")
	public void algo(HttpServletResponse httpResponse) throws AuthorizationFailure, IOException {
		
		String redirectUrl = m.getAuthUrl("http://localhost:3001/autenticar");
		System.out.println(redirectUrl);
		//m.authorize("TG-5bae4ba1d697550006285830-109521814", "http://localhost:3001");
		httpResponse.sendRedirect(redirectUrl);
	}
	
	@GetMapping(value = "/autenticar")
	public void autenticar(HttpServletResponse httpResponse, @RequestParam("code") String code) throws AuthorizationFailure, MeliException, IOException {
		System.out.println("se redirigio aqui " + code);
		m.authorize(code, "http://localhost:3001/autenticar");
		httpResponse.sendRedirect("http://localhost:3001/buscar");
		System.out.println(m.getAccessToken());		
		
	}
	
	@GetMapping(value = "/buscar")
	@ResponseBody
	public Object buscar(HttpServletResponse httpResponse, @RequestParam("item") String item) throws IOException, MeliException {
				
		FluentStringsMap params = new FluentStringsMap();
		params.add("q", item);
		params.add("access_token", m.getAccessToken());
		Response response = m.get("/sites/MCO/search", params);
		//System.out.println(response.getUri());
		//System.out.println(response.getResponseBody());
		
		return response.getResponseBody();		
	}
}
