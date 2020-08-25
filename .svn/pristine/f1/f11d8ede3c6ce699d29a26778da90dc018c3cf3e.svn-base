/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.entidades.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import sistemarlf.modelo.entidades.Registro;

/**
 *
 * @author Jhonnatan
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente extends Registro {
    
    @Column
    private String nomeRazaoSocial;
    @Column
    private String cpfCnpj;
    @Column
    private Boolean ehPJ;
    @Column
    private String nomeContato;
    @Column
    private String telefone;
    @Column
    private String telefoneAlternativo;
    @Column
    private String telefoneCelular;
    @Column
    private String Endereco;
    @Column
    private String email;

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }
    
    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public Boolean getEhPJ() {
        return ehPJ;
    }

    public void setEhPJ(Boolean ehPJ) {
        this.ehPJ = ehPJ;
    }
    
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefoneAlternativo() {
        return telefoneAlternativo;
    }

    public void setTelefoneAlternativo(String telefoneAlternativo) {
        this.telefoneAlternativo = telefoneAlternativo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
