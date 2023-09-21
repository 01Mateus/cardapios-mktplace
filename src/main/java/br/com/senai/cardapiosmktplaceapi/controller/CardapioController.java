package br.com.senai.cardapiosmktplaceapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.cardapiosmktplaceapi.entity.Cardapio;
import br.com.senai.cardapiosmktplaceapi.entity.OpcaoDoCardapio;
import br.com.senai.cardapiosmktplaceapi.service.CardapioService;
import br.com.senai.cardapiosmktplaceapi.service.RestauranteService;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {

	@Autowired
	@Qualifier("cardapioServiceImplProxy")
	private CardapioService service;
	
	@Autowired
	@Qualifier("restauranteServiceProxy")
	private RestauranteService restauranteService;
	
	private Map<String, Object> converter(Cardapio cardapio){
		Map<String, Object> cardapioMap = new HashMap<String, Object>();
		cardapioMap.put("id", cardapio.getId());
		cardapioMap.put("nome", cardapio.getNome());
		cardapioMap.put("descricao", cardapio.getDescricao());
		cardapioMap.put("status", cardapio.getStatus());
		Map<String, Object> restauranteMap = new HashMap<String, Object>();
		restauranteMap.put("id", cardapio.getRestaurante().getId());
		restauranteMap.put("nome", cardapio.getRestaurante().getNome());
		cardapioMap.put("restaurante", restauranteMap);
		List<Map<String, Object>> opcoesMap = new ArrayList<Map<String, Object>>();
		for (OpcaoDoCardapio opcaoDoCarpadio : cardapio.getOpcoes()) {
			Map<String, Object> opcaoMap = new HashMap<String, Object>();
			opcaoMap.put("id", opcaoDoCarpadio.getOpcao().getId());
			opcaoMap.put("nome", opcaoDoCarpadio.getOpcao().getNome());
			opcaoMap.put("promocao", opcaoDoCarpadio.getOpcao().getPromocao());
			opcaoMap.put("recomendado", opcaoDoCarpadio.getPreco());
			Map<String, Object> secaoMap = new HashMap<String, Object>();
			secaoMap.put("id", opcaoDoCarpadio.getSecao().getId());
			secaoMap.put("nome", opcaoDoCarpadio.getSecao().getNome());
			opcoesMap.add(opcaoMap);
		}
		cardapioMap.put("opcoes", opcoesMap);
		return cardapioMap;
	}
	
}
