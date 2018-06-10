package idwall.desafio;

import idwall.desafio.builder.TextoBuilder;
import static idwall.desafio.builder.TextoBuilder.*;
import idwall.desafio.exception.PalavraMaiorException;
import idwall.desafio.string.IdwallFormatter;
import idwall.desafio.string.StringFormatter;
import java.util.Arrays;
import java.util.Collection;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author ricci
 */
@RunWith(Parameterized.class)
public class IdwallFormatterParameterizedTest {

    private StringFormatter desafio;

    @Parameter
    public String textoTitulo;

    @Parameter(value = 1)
    public String texto;

    @Parameter(value = 2)
    public int caracteres;

    @Parameter(value = 3)
    public String esperado;

    @Parameter(value = 4)
    public boolean justificado;

    @Parameters(name = "{0} {2} caracteres justificado {4}")
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][]{
            {"BIBLIA", BIBLIA_SEM_FORMATACAO, 40, BIBLIA_40_CARACTERES, false},
            {"BIBLIA", BIBLIA_SEM_FORMATACAO, 40, BIBLIA_40_CARACTERES_JUSTIFICADO, true},
            {"BIBLIA", BIBLIA_SEM_FORMATACAO, 20, BIBLIA_20_CARACTERES_JUSTIFICADO, true},
            {"BIBLIA", BIBLIA_SEM_FORMATACAO, 20, BIBLIA_20_CARACTERES, false},
            {"BIBLIA", BIBLIA_SEM_FORMATACAO, 10, BIBLIA_10_CARACTERES, false},
            {"VIDA", VIDA_SEM_FORMATACAO, 40, VIDA_40_CARACTERES, false},
            {"VIDA", VIDA_SEM_FORMATACAO, 20, VIDA_20_CARACTERTES, false}

        });
    }

    @Before
    public void inicia() {
        desafio = new IdwallFormatter();
    }

    @Test
    public void deveResolverTextoBiblia40Caracteres() throws PalavraMaiorException {
        String result = desafio.format(texto, caracteres, justificado);
        Assert.assertEquals(result, esperado);
    }

}
