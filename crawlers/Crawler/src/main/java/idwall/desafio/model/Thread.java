/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idwall.desafio.model;

/**
 *
 * @author ricci
 */
public class Thread {

    private String titulo;
    private String subreddit;
    private String link;
    private long upvotes;
    private String linkComentarios;
    private String autor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(long upvotes) {
        this.upvotes = upvotes;
    }

    public String getLinkComentarios() {
        return linkComentarios;
    }

    public void setLinkComentarios(String linkComentarios) {
        this.linkComentarios = linkComentarios;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "======================================================================\n"
                + "TÍTULO: " + titulo + "\n"
                + "UPVOTES: " + upvotes + "\n"
                + "AUTOR: " + autor + "\n"
                + "SUBREDDIT: " + subreddit + "\n"
                + "LINK: " + link + "\n"
                + "LINK COMENTÁRIOS: " + linkComentarios + "\n";

    }

}
