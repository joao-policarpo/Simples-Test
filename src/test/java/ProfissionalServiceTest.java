import dto.ProfissionalDTO;
import entity.Cargo;
import entity.Profissional;
import mappers.ProfissionalMapper;
import repository.ProfissionalRepository;
import service.ProfissionalService;
import utils.VerificarCamposProfissional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfissionalServiceTest {

    @InjectMocks
    private ProfissionalService profissionalService;

    @Mock
    private ProfissionalRepository profissionalRepository;

    @Mock
    private VerificarCamposProfissional verificarCamposProfissional;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarProfissional_Sucesso() {
        // ARRANGE
        ProfissionalDTO dto = new ProfissionalDTO();
        dto.setNome("João");
        dto.setCargo(Cargo.DESENVOLVEDOR);

        Calendar cal = Calendar.getInstance();
        cal.set(1990, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date nascimento = cal.getTime();
        dto.setNascimento(nascimento);

        Profissional profissionalEntity = ProfissionalMapper.toEntity(dto);
        profissionalEntity.setId(1L);
        profissionalEntity.setAtivo(true);

        when(profissionalRepository.save(any(Profissional.class))).thenReturn(profissionalEntity);

        // ACT
        ProfissionalDTO resultado = profissionalService.salvar(dto);

        // ASSERT
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("João", resultado.getNome());
        verify(verificarCamposProfissional).validarProfissionalDTOPost(dto);
        verify(profissionalRepository).save(any(Profissional.class));
    }

    @Test
    public void testBuscarPorId_Sucesso() {
        // ARRANGE
        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setNome("Maria");
        profissional.setCargo(Cargo.TESTER);

        Calendar cal = Calendar.getInstance();
        cal.set(1985, Calendar.MAY, 10, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date nascimento = cal.getTime();
        profissional.setNascimento(nascimento);
        profissional.setAtivo(true);

        when(profissionalRepository.findById(1L)).thenReturn(Optional.of(profissional));

        // ACT
        ProfissionalDTO dto = profissionalService.buscarPorId(1L);

        // ASSERT
        assertNotNull(dto);
        assertEquals("Maria", dto.getNome());
        assertEquals(Cargo.TESTER, dto.getCargo());
    }

    @Test
    public void testExcluirLogicamente_Sucesso() {
        // ARRANGE
        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setAtivo(true);

        when(profissionalRepository.findById(1L)).thenReturn(Optional.of(profissional));
        when(profissionalRepository.save(any(Profissional.class))).thenReturn(profissional);

        // ACT
        profissionalService