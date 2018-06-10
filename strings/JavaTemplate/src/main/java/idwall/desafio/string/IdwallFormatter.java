package idwall.desafio.string;

import idwall.desafio.exception.PalavraMaiorException;
import idwall.desafio.validate.IdwallFormatterValidate;

/**
 * @author Gabriel Ricci
 *
 */
public class IdwallFormatter extends StringFormatter {

    public static String newline = System.getProperty("line.separator");

    /**
     * Should format as described in the challenge
     *
     * @param text
     * @param limit
     * @param justify
     * @return
     * @throws idwall.desafio.exception.PalavraMaiorException
     */
    @Override
    public String format(String text, int limit, boolean justify) throws PalavraMaiorException {
        StringBuilder stringBuilder = new StringBuilder(text);
        try {
            new IdwallFormatterValidate().validarTextoEntrada(text, limit);

            for (int i = limit; i <= stringBuilder.length(); i += limit) {

                char primeiroCaracter = stringBuilder.charAt(i);
                String[] linhas = stringBuilder.substring(i - limit + 2, i).split(newline);

                if (linhas.length >= 2) {
                    for (int x = 1; x <= linhas.length - 1; x++) {
                        i -= linhas[x].length();
                    }
                } else {
                    while (primeiroCaracter != ' ') {
                        i--;
                        primeiroCaracter = stringBuilder.charAt(i);
                    }
                    stringBuilder.deleteCharAt(i);
                    stringBuilder.insert(i, newline);
                }
            }
            if (justify) {
                stringBuilder = justify(stringBuilder.toString(), limit);
            }
        } catch (PalavraMaiorException ex) {
            throw ex;
        }
        return stringBuilder.toString();
    }

    private StringBuilder justify(String text, int limit) {
        StringBuilder builder = new StringBuilder();
        String[] linhas = text.split(newline);
        for (String linha : linhas) {
            StringBuilder builderEspaco = new StringBuilder();
            String[] espacos = linha.split(" ");
            int espacosRequeridos = (espacos.length - 1) + (limit - linha.length());

            int x = 1;
            while ((x <= espacos.length - 1) && (espacosRequeridos > 0)) {
                StringBuilder builderTmp = new StringBuilder(espacos[x]);
                builderTmp.insert(0, " ");
                espacos[x] = builderTmp.toString();
                espacosRequeridos--;
                x++;
                if ((espacosRequeridos > 0) && (x == espacos.length)) {
                    x = 1;
                }
            }
            for (String espaco : espacos) {
                builderEspaco.append(espaco);
            }
            if (!linha.equals(linhas[linhas.length - 1])) {
                builder.append(builderEspaco.toString().concat(newline));
            } else {
                builder.append(builderEspaco.toString());
            }
        }
        return builder;
    }
}
