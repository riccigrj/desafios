/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idwall.desafio.validate;

import idwall.desafio.exception.PalavraMaiorException;
import idwall.desafio.string.IdwallFormatter;

/**
 *
 * @author ricci
 */
public class IdwallFormatterValidate {

    public void validarTextoEntrada(String text, int limit) throws PalavraMaiorException {
        String[] linhas = text.split(IdwallFormatter.newline);
        for (String linha : linhas) {
            for (String palavra : linha.split(" ")) {
                if (palavra.length() > limit) {
                    throw new PalavraMaiorException("Existem palavras maiores do que o limite estabelecido.\n"
                            + "Não será possível efetuar a formatação.");
                }
            }
        }
    }
}
