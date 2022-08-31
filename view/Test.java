package view;

import java.util.Vector;

import materia.MateriaHistorico;

public class Test{

  public static void main(String[] args){
    TelaInicial tela = TelaInicial.getInstance();
    tela.setVisible(true);

    Vector<Vector<MateriaHistorico>> mat = new Vector<Vector<MateriaHistorico>>();
    for(int i = 0; i < 10; i++){
      Vector<MateriaHistorico> toadd = new Vector<MateriaHistorico>();
      MateriaHistorico temp = new MateriaHistorico();
      temp.setCodCurso("CI"+i);
      temp.setNomeAtivCurric("Nome"+i);
      temp.setMediaFinal("Media"+1);
      temp.setChTotal("CH"+i);
      toadd.add(temp);
      mat.add(toadd);
    }

    TelaEstado telaest = TelaEstado.getInstance();
    telaest.setMateriasCursadas(mat);
  }

}
