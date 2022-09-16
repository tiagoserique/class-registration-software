package materia;

import java.io.Serializable;

public class Materia implements Serializable{
  private static final long serialVersionUID = 0;
  private String codCurso;
  private int numVersao;
  private String descrEstrutura;
  private String codDisci;
  private String nomeUnidade;
  private String nomeDisci;
  private int periodo;
  private int numHoras;
  private String tipoDisci;
  private int chTotal;
  private String situacao;

  public String toString(){
    return (this.codCurso + ";" + this.numVersao + ";" + this.descrEstrutura + ";" +
        this.codDisci + ";" + this.nomeUnidade + ";" + this.nomeDisci + ";" + 
        this.periodo + ";" + this.numHoras + ";" + this.tipoDisci + ";" + 
        this.situacao + ";" + this.chTotal);
  }
  //getters
  public String getCodCurso()       { return codCurso;       }
  public int    getNumVersao()      { return numVersao;      }
  public String getDescrEstrutura() { return descrEstrutura; }
  public String getCodDisci()       { return codDisci;       }
  public String getNomeUnidade()    { return nomeUnidade;    }
  public String getNomeDisci()      { return nomeDisci;      }
  public int    getPeriodo()        { return periodo;        }
  public int    getNumHoras()       { return numHoras;       }
  public String getTipoDisci()      { return tipoDisci;      }
  public String getSituacao()       { return situacao;       }
  public int    getChTotal()        { return chTotal;        }
  //setters
  public void setCodCurso      (String codCurso)       { this.codCurso=codCurso;             }
  public void setNumVersao     (int numVersao)         { this.numVersao=numVersao;           }
  public void setDescrEstrutura(String descrEstrutura) { this.descrEstrutura=descrEstrutura; }
  public void setCodDisci      (String codDisci)       { this.codDisci=codDisci;             }
  public void setNomeUnidade   (String nomeUnidade)    { this.nomeUnidade=nomeUnidade;       }
  public void setNomeDisci     (String nomeDisci)      { this.nomeDisci=nomeDisci;           }
  public void setPeriodo       (int periodo)           { this.periodo=periodo;               }
  public void setNumHoras      (int numHoras)          { this.numHoras=numHoras;             }
  public void setTipoDisci     (String tipoDisci)      { this.tipoDisci=tipoDisci;           }
  public void setSituacao      (String situacao)       { this.situacao=situacao;             }
  public void setChTotal       (int chTotal)           { this.chTotal=chTotal;               }
}
