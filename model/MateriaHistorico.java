package model;

import java.io.Serializable;

public class MateriaHistorico implements Serializable {
    private static final long serialVersionUID = 0;
    private String matrAluno;
    private String nomePessoa;
    private String codCurso;
    private String nomeCurso;
    private String numVersao;
    private String ano;
    private String mediaFinal;
    private String situacaoItem;
    private String periodo;
    private String situacao;
    private String codAtivCurric;
    private String nomeAtivCurric;
    private String chTotal;
    private String descrEstrutura;
    private String frequencia;
    private String sigla;

    @Override
    public String toString() {
        return "MateriaHistorico [ano=" + ano + ", chTotal=" + chTotal + ", codAtivCurric=" + codAtivCurric
                + ", codCurso=" + codCurso + ", descrEstrutura=" + descrEstrutura + ", frequencia=" + frequencia
                + ", matrAluno=" + matrAluno + ", mediaFinal=" + mediaFinal + ", nomeAtivCurric=" + nomeAtivCurric
                + ", nomeCurso=" + nomeCurso + ", nomePessoa=" + nomePessoa + ", numVersao=" + numVersao + ", periodo="
                + periodo + ", sigla=" + sigla + ", situacao=" + situacao + ", situacaoItem=" + situacaoItem + "]";
    }


    public String getMatrAluno    () { return matrAluno;      }
    public String getNomePesso    () { return nomePessoa;     }
    public String getCodCurso     () { return codCurso;       }
    public String getNomeCurso    () { return nomeCurso;      }
    public String getNumVersao    () { return numVersao;      }
    public String getAno          () { return ano;            }
    public String getMediaFinal   () { return mediaFinal;     }
    public String getSituacaoItem () { return situacaoItem;   }
    public String getPeriodo      () { return periodo;        }
    public String getSituacao     () { return situacao;       }
    public String getCodAtivCurric() { return codAtivCurric;  }
    public String getNomeAtivCurri() { return nomeAtivCurric; }
    public String getChTotal      () { return chTotal;        }
    public String getDescrEstrutur() { return descrEstrutura; }
    public String getFrequenci    () { return frequencia;     }
    public String getSigla        () { return sigla;          }

    public void setMatrAluno     (String matrAluno)     { this.matrAluno = matrAluno; }
    public void setNomePessoa    (String nomePessoa)    { this.nomePessoa = nomePessoa; }
    public void setCodCurso      (String codCurso)      { this.codCurso = codCurso; }
    public void setNomeCurso     (String nomeCurso)     { this.nomeCurso = nomeCurso; }
    public void setNumVersao     (String numVersao)     { this.numVersao = numVersao; }
    public void setAno           (String ano)           { this.ano = ano; }
    public void setMediaFinal    (String mediaFinal)    { this.mediaFinal = mediaFinal; }
    public void setSituacaoItem  (String situacaoItem)  { this.situacaoItem = situacaoItem; }
    public void setPeriodo       (String periodo)       { this.periodo = periodo; }
    public void setSituacao      (String situacao)      { this.situacao = situacao; }
    public void setCodAtivCurric (String codAtivCurric) { this.codAtivCurric = codAtivCurric; }
    public void setNomeAtivCurric(String nomeAtivCurric){ this.nomeAtivCurric = nomeAtivCurric; }
    public void setChTotal       (String chTotal)       { this.chTotal = chTotal; }
    public void setDescrEstrutura(String descrEstrutura){ this.descrEstrutura = descrEstrutura; }
    public void setFrequencia    (String frequencia)    { this.frequencia = frequencia; }
    public void setSigla         (String sigla)         { this.sigla = sigla; }
}
