package idwall.desafio;

import idwall.desafio.builder.TextoBuilder;
import idwall.desafio.exception.PalavraMaiorException;
import idwall.desafio.string.IdwallFormatter;
import idwall.desafio.string.StringFormatter;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ricci
 */
public class IdwallFormatterTest {

    private StringFormatter desafio;

    @Before
    public void inicia() {
        desafio = new IdwallFormatter();
    }

    @Test(expected = PalavraMaiorException.class)
    public void deveDispararPalavraMaiorException() throws PalavraMaiorException {
        String texto = TextoBuilder.BIBLIA_SEM_FORMATACAO;
        desafio.format(texto, 5, true);
    }
}
