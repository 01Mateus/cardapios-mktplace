package br.com.senai.cardapiosmktplaceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.senai.cardapiosmktplaceapi.entity.Secao;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
public interface SecaoService {
	
	public Secao salvar(
			@NotNull(message = "A seção é obrigatória")
			Secao secao);
	
	public void atualiarStatusPor(
			@NotNull(message = "O id é obrigatório")
			Integer id,
			@NotNull(message = "O status é obrigatório")
			Status status);
	
	public Page<Secao> listarPor(
			@NotBlank(message = "O nome é obrigatório")
			@Size(min = 3, message = "O nome para listagem deve conter no mínimo 3 caracteres")
			String nome,
			@NotNull(message = "O status é obrigatório")
			Pageable paginacao);
	
	public Secao buscarPor(
			@NotNull(message = "O id para busca é obrigatório")
			@Positive(message = "O id para busca deve ser positivo")
			Integer id);
	
	public Secao excluirPor(
			@NotNull(message = "O id para remoção é obrigatório")
			@Positive(message = "O id para remoção deve ser positivo")
			Integer id);

}
