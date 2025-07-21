package services;

import dto.ContatoDTO;
import jakarta.persistence.EntityManager;
import models.Contatos;
import models.Profissional;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import mappers.ContatoMapper;
import play.db.jpa.JPAApi;
import utils.VerificarCamposContato;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContatoService {

    private final JPAApi jpaApi;
    private final VerificarCamposContato verificarCamposContato;

    @Inject
    public ContatoService(JPAApi jpaApi, VerificarCamposContato verificarCamposContato) {
        this.jpaApi = jpaApi;
        this.verificarCamposContato = verificarCamposContato;
    }

    public List<ContatoDTO> listarTodos() {
        return jpaApi.withTransaction((EntityManager em) ->
                em.createQuery("SELECT c FROM Contatos c JOIN FETCH c.profissional", Contatos.class)
                        .getResultList()
                        .stream()
                        .map(ContatoMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }

    public List<Map<String, Object>> listarContatosComFiltroECampos(String q, List<String> fields) {
        return jpaApi.withTransaction(em -> {
            List<Contatos> contatos = em.createQuery("SELECT c FROM Contatos c JOIN FETCH c.profissional", Contatos.class)
                    .getResultList();

            if (q != null && !q.isBlank()) {
                String filtro = q.toLowerCase();
                contatos = contatos.stream()
                        .filter(c -> (c.getNome() != null && c.getNome().toLowerCase().contains(filtro)) || (c.getContato() != null && c.getContato().toLowerCase().contains(filtro))
                        )
                        .collect(Collectors.toList());
            }

            return contatos.stream()
                    .map(contato -> verificarCamposContato.filtrarCampos(contato, fields))
                    .collect(Collectors.toList());
        });
    }

    public ContatoDTO buscarPorId(Long id) {
        return jpaApi.withTransaction(em -> {
            Contatos contato = em.find(Contatos.class, id);
            if (contato == null) {
                throw new EntityNotFoundException("Contato com ID " + id + " não encontrado");
            }
            return ContatoMapper.toDTO(contato);
        });
    }

    public ContatoDTO criar(ContatoDTO dto) {
        verificarCamposContato.validarContatoDTOPost(dto);

        return jpaApi.withTransaction(em -> {
            Profissional profissional = em.find(Profissional.class, dto.getProfissional());
            if (profissional == null) {
                throw new EntityNotFoundException("Profissional com ID " + dto.getProfissional() + " não encontrado");
            }

            // Verificar se já existe contato para este profissional
            List<Contatos> existentes = em.createQuery(
                            "SELECT c FROM Contatos c WHERE c.profissional.id = :id", Contatos.class)
                    .setParameter("id", dto.getProfissional())
                    .getResultList();

            if (!existentes.isEmpty()) {
                throw new IllegalArgumentException("Este profissional já possui um contato cadastrado");
            }

            Contatos entidade = ContatoMapper.toEntity(dto);
            entidade.setProfissional(profissional);

            em.persist(entidade);
            return ContatoMapper.toDTO(entidade);
        });
    }

    public ContatoDTO atualizar(Long id, ContatoDTO dto) {
        verificarCamposContato.validarContatoDTOPut(dto);

        return jpaApi.withTransaction(em -> {
            Contatos existente = em.find(Contatos.class, id);
            if (existente == null) {
                throw new EntityNotFoundException("Contato com ID " + id + " não encontrado");
            }

            if (dto.getNome() != null) {
                existente.setNome(dto.getNome());
            }

            if (dto.getContato() != null) {
                existente.setContato(dto.getContato());
            }

            if (dto.getProfissional() != null) {
                Profissional profissional = em.find(Profissional.class, dto.getProfissional());
                if (profissional == null) {
                    throw new EntityNotFoundException("Profissional com ID " + dto.getProfissional() + " não encontrado");
                }

                // Impede atualização para outro profissional que já tem contato
                List<Contatos> existentes = em.createQuery(
                                "SELECT c FROM Contatos c WHERE c.profissional.id = :id AND c.id != :contatoId", Contatos.class)
                        .setParameter("id", dto.getProfissional())
                        .setParameter("contatoId", id)
                        .getResultList();

                if (!existentes.isEmpty()) {
                    throw new IllegalArgumentException("Este profissional já possui outro contato cadastrado");
                }

                existente.setProfissional(profissional);
            }

            em.merge(existente);
            return ContatoMapper.toDTO(existente);
        });
    }

    public void deletar(Long id) {
        jpaApi.withTransaction(em -> {
            Contatos contato = em.find(Contatos.class, id);
            if (contato == null) {
                throw new EntityNotFoundException("Contato com ID " + id + " não encontrado");
            }

            Profissional profissional = contato.getProfissional();
            if (profissional != null) {
                profissional.getContatos().remove(contato);
            }

            em.remove(contato);
            return null;
        });
    }
}
