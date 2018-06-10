package idwall.desafio.string;

import idwall.desafio.exception.PalavraMaiorException;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public abstract class StringFormatter {

    private Integer limit;

    public StringFormatter() {
        this.limit = 40;
    }

    /**
     * It receives a text and should return it formatted
     *
     * @param text
     * @param limit
     * @param justify
     * @return
     */
    public abstract String format(String text, int limit, boolean justify) throws PalavraMaiorException;
}
