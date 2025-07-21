import app.dto.ContatoDTO;
import app.models.Contatos;
import app.models.Profissional;
import app.mappers.ContatoMapper;
import app.repositories.ContatoRepository;
import app.services.ContatoService;
import app.utils.VerificarCamposContato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContatoServiceTest {

    @InjectMocks
    private ContatoService contatoService;

    @Mock
    private ContatoRepository contatoRepository;

    @Mock
    private VerificarCamposContato verificarCamposContato;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTodosSucesso() {
        // ARRANGE
        Contatos contato1 = new Contatos();
        contato1.setId(1L);
        contato1.setNome("casa");
        contato1.setContato("123456789");

        Contatos contato2 = new Contatos();
        contato2.setId(2L);
        contato2.setNome("celular");
        contato2.setContato("987654321");

        List<Contatos> listaContatos = Arrays.asList(contato1, contato2);
        when(contatoRepository.findAll()).thenReturn(listaContatos);

        try (MockedStatic<ContatoMapper> mockedMapper = mockStatic(ContatoMapper.class)) {
            mockedMapper.when(() -> ContatoMapper.toDTO(contato1)).thenReturn(new ContatoDTO(1L, "casa", "123456789", null, null));
            mockedMapper.when(() -> ContatoMapper.toDTO(contato2)).thenReturn(new ContatoDTO(2L, "celular", "987654321", null, null));

            // ACT
            List<ContatoDTO> resultado = contatoService.listarTodos();

            // ASSERT
            assertEquals(2, resultado.size());
            assertEquals("casa", resultado.get(0).getNome());
            assertEquals("celular", resultado.get(1).getNome());
        }
    }

    @Test
    public void testListarContatosComFiltroECamposFiltraPorNome() {
        // ARRANGE
        Contatos contato1 = new Contatos();
        contato1.setId(1L);
        contato1.setNome("fixo casa");
        contato1.setContato("123456");

        Contatos contato2 = new Contatos();
        contato2.setId(2L);
        contato2.setNome("celular");
        contato2.setContato("654321");

        List<Contatos> listaContatos = Arrays.asList(contato1, contato2);
        when(contatoRepository.findAll()).thenReturn(listaContatos);

        when(verificarCamposContato.filtrarCampos(any(Contatos.class), anyList())).thenAnswer(invocation -> {
            Contatos c = invocation.getArgument(0);
            List<String> fields = invocation.getArgument(1);

            Map<String, Object> map = new HashMap<>();
            boolean allFields = (fields == null || fields.isEmpty());
            if (allFields || fields.contains("id")) map.put("id", c.getId());
            if (allFields || fields.contains("nome")) map.put("nome", c.getNome());
            if (allFields || fields.contains("contato")) map.put("contato", c.getContato());
            return map;
        });

        // ACT
        List<Map<String, Object>> resultado = contatoService.listarContatosComFiltroECampos("fixo", Arrays.asList("nome", "contato"));

        // ASSERT
        assertEquals(1, resultado.size());
        assertEquals("fixo casa", resultado.get(0).get("nome"));
    }

    @Test
    public void testSalvarContatoSucesso() {
        // ARRANGE
        ContatoDTO dto = new ContatoDTO();
        dto.setNome("trabalho");
        dto.setContato("999888777");
        dto.setProfissional(2L);

        Profissional profissional = new Profissional();
        profissional.setId(2L);

        Contatos entidade = new Contatos();
        entidade.setId(10L);
        entidade.setNome("trabalho");
        entidade.setContato("999888777");
        entidade.setProfissional(profissional);

        doNothing().when(verificarCamposContato).validarContatoDTOPost(dto);
        when(contatoRepository.findProfissionalContatos(2L)).thenReturn(Collections.emptyList());
        when(contatoRepository.findProfissionalById(2L)).thenReturn(Optional.of(profissional));

        try (MockedStatic<ContatoMapper> mockedMapper = mockStatic(ContatoMapper.class)) {
            mockedMapper.when(() -> ContatoMapper.toEntity(dto)).thenReturn(entidade);
            mockedMapper.when(() -> ContatoMapper.toDTO(entidade)).thenReturn(dto);

            when(contatoRepository.save(any(Contatos.class))).thenReturn(entidade);

            // ACT
            ContatoDTO resultado = contatoService.criar(dto);

            // ASSERT
            assertNotNull(resultado);
            assertEquals("trabalho", resultado.getNome());
            verify(contatoRepository).save(any(Contatos.class));
        }
    }

    @Test
    public void testSalvarContatoDuplicado() {
        // ARRANGE
        ContatoDTO dto = new ContatoDTO();
        dto.setNome("duplicado");
        dto.setContato("111222333");
        dto.setProfissional(3L);

        Profissional profissional = new Profissional();
        profissional.setId(3L);

        Contatos existente = new Contatos();
        existente.setProfissional(profissional);

        doNothing().when(verificarCamposContato).validarContatoDTOPost(dto);
        when(contatoRepository.findProfissionalById(3L)).thenReturn(Optional.of(profissional));
        when(contatoRepository.findProfissionalContatos(3L)).thenReturn(List.of(existente));

        // ACT
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contatoService.criar(dto));

        // ASSERT
        assertEquals("Este profissional já possui um contato cadastrado", ex.getMessage());
    }
}
