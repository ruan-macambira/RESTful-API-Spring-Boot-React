package com.rhgreat.api.restful;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
class Usuario {
    
    private @Id @GeneratedValue Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String nomeMae;

    @NotBlank
    @Size(min = 11, max = 11)
    @CpfValidoConstraint
    private String cpf;

    @NotBlank
    private String rg;

    private Date dataNascimento;
    private Date dataCadastro;

    Usuario() {}

    Usuario(String nome, String nomeMae, String cpf, String rg) {
        this.nome = nome;
        this.nomeMae = nomeMae;
        this.cpf = cpf;
        this.rg = rg;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return this.nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return this.rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id)
            && Objects.equals(nome, usuario.nome)
            && Objects.equals(nomeMae, usuario.nomeMae)
            && Objects.equals(cpf, usuario.cpf)
            && Objects.equals(rg, usuario.rg)
            && Objects.equals(dataNascimento, usuario.dataNascimento)
            && Objects.equals(dataCadastro, usuario.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, nomeMae, cpf, rg, dataNascimento, dataCadastro);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", nomeMae='" + getNomeMae() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", rg='" + getRg() + "'" +
            ", dataNascimento='" + getDataNascimento() + "'" +
            ", dataCadastro='" + getDataCadastro() + "'" +
            "}";
    }

}
