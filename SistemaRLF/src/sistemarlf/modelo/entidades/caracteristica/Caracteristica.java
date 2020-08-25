/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.entidades.caracteristica;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import sistemarlf.modelo.entidades.Registro;

/**
 *
 * @author Jhonnatan
 */
@Entity
public class Caracteristica extends Registro {
    @Column
    private String nome;
    @ManyToOne
    private Caracteristica pai;
    @OneToMany(mappedBy = "pai")
    private List<Caracteristica> filhas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Caracteristica getPai() {
        return pai;
    }

    public void setPai(Caracteristica pai) {
        this.pai = pai;
    }

    public List<Caracteristica> getFilhas() {
        return filhas;
    }

    public void setFilhas(List<Caracteristica> filhas) {
        this.filhas = filhas;
    }
}
