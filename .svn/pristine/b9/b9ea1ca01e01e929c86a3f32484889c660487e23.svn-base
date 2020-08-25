/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarlf.modelo.entidades.produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import sistemarlf.modelo.entidades.Registro;
import sistemarlf.modelo.entidades.caracteristica.Caracteristica;
import sistemarlf.modelo.oad.produto.OADProduto;
import uteis.NumberFieldVerifier;

/**
 *
 * @author Jhonnatan
 */
@Entity
public class Produto extends Registro {
    @Column
    private Integer CodigoReferencia;
    @Column
    private Double valorVenda;
    @Column
    private Double valorCusto;    
    @Column
    private String descricao;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    @ManyToMany
    @JoinTable(name = "caracteristica_produto", 
            joinColumns = { @JoinColumn(name = "produto") }, 
            inverseJoinColumns = { @JoinColumn(name = "caracteristica") })
    private List<Caracteristica> caracteristicas;

    public Integer getCodigoReferencia() {
        return CodigoReferencia;
    }

    public void setCodigoReferencia(Integer CodigoReferencia) {
        this.CodigoReferencia = CodigoReferencia;
    }
    public Double getValorVenda() {
        return valorVenda;
    }
    public String getValorVendaFormatado() {
        return NumberFieldVerifier.SetValorFormatado(getValorVenda());
    }
    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public List<Caracteristica> getCaracteristicas(Integer codCaracteristicaPai) {
        caracteristicas = new OADProduto().CarregaComCaracteristicas(this).caracteristicas;
        if (!codCaracteristicaPai.equals(1) && !codCaracteristicaPai.equals(2))
            return caracteristicas;
        
        List<Caracteristica> retorno = new ArrayList<Caracteristica>();
        for (int i = 0; i < caracteristicas.size(); i++){
            if (caracteristicas.get(i).getPai().getCodigo().equals(codCaracteristicaPai))
                retorno.add(caracteristicas.get(i));
        }
        return retorno;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Double getValorCusto() {
        return valorCusto;
    }
    public String getValorCustoFormatado() {
        return NumberFieldVerifier.SetValorFormatado(getValorCusto());
    }
    public void setValorCusto(Double valorCusto) {
        this.valorCusto = valorCusto;
    }
    
    public Object[] getLinha() {
        Object linha[] = {getCodigoReferencia(), getDescricao(), getValorCustoFormatado(), getValorVendaFormatado(), getDataInclusao()};
        
        return linha;
    }
}
