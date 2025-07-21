package services;

import dto.ProfissionalDTO;
import jakarta.persistence.EntityManager;
import models.Profissional;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import mappers.ProfissionalMapper;
import play.db.jpa.JPAApi;
import utils.VerificarCamposProfissional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfissionalService {

    private final JPAApi jpaApi;
    private final VerificarCamposProfissional verificarCamposProfissional;

    @Inject
    public ProfissionalService(JPAApi jpaApi, VerificarCamposProfissional verificarCamposProfissional) {
        this.jpaApi = jpaApi;
        this.verificarCamposProfissional = verificarCamposProfissional;
    }

    public List<ProfissionalDTO> listarTodos() {
        return jpaApi.withTransaction((EntityManager em) ->
                em.createQuery("SELECT p FROM Profissional p WHERE p.ativo = true", Profissional.class)
                        .getResultList()
                        .stream()
                        .map(ProfissionalMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }

    public List<Map<String, Object>> listarComFiltroECampos(String q, List<String> fields) {
        return jpaApi.withTransaction(em -> {
            List<Profissional> profissionais = em.createQuery("SELECT p FROM Profissional p WHERE p.ativo = true", Profissional.class)
                    .getResultList();

            if (q != null && !q.isBlank()) {
                String filtro = q.toLowerCase();
                profissionais = profissionais.stream()
                        .filter(p ->
                                (p.getNome() != null && p.getNome().toLowerCase().contains(filtro)) ||
                                        (p.getCargo() != null && p.getCargo().name().toLowerCase().contains(filtro))
                        )
                        .collect(Collectors.toList());
            }

            boolean retornarTodosCampos = (fields == null || fields.isEmpty());

            return profissionais.stream()
                    .map(p -> verificarCamposProfissional.filtrarCamposeSerializar(p, fields, retornarTodosCampos))
                    .collect(Collectors.toList());
        });
    }

    public ProfissionalDTO buscarPorId(Long id) {
        return jpaApi.withTransaction(em -> {
            Profissional profissional = em.find(Profissional.class, id);
            if (profissional == null || Boolean.FALSE.equals(profissional.getAtivo())) {
                throw new EntityNotFoundException("Profissional não encontrado");
            }
            return ProfissionalMapper.toDTO(profissional);
        });
    }

    public ProfissionalDTO salvar(ProfissionalDTO dto) {
        verificarCamposProfissional.validarProfissionalDTOPost(dto);

        return jpaApi.withTransaction(em -> {
            Profissional profissional = ProfissionalMapper.toEntity(dto);
            profissional.setAtivo(true);
            em.persist(profissional);
            return ProfissionalMapper.toDTO(profissional);
        });
    }

    public ProfissionalDTO atualizar(Long id, ProfissionalDTO dto) {
        return jpaApi.withTransaction(em -> {
            Profissional existente = em.find(Profissional.class, id);
            if (existente == null || Boolean.FALSE.equals(existente.getAtivo())) {
                throw new EntityNotFoundException("Profissional não encontrado");
            }

            verificarCamposProfissional.aplicarCamposSePresentes(dto, existente);
            em.merge(existente);
            return ProfissionalMapper.toDTO(existente);
        });
    }

    public void excluirLogicamente(Long id) {
        jpaApi.withTransaction(em -> {
            Profissional profissional = em.find(Profissional.class, id);
            if (profissional == null || Boolean.FALSE.equals(profissional.getAtivo())) {
                throw new EntityNotFoundException("Profissional não encontrado");
            }

            profissional.setAtivo(false);
            em.merge(profissional);
            return null;
        });
    }
}
