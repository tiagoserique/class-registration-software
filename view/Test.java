package view;

import java.util.Vector;

import materia.MateriaHistorico;
import materia.Materia;

public class Test implements TelaSub{

  public static void main(String[] args){
    TelaInicial tela = TelaInicial.getInstance();
    tela.setVisible(true);

    Vector<Vector<MateriaHistorico>> mat = new Vector<Vector<MateriaHistorico>>();
    for(int i = 0; i < 10; i++){
      Vector<MateriaHistorico> toadd = new Vector<MateriaHistorico>();
      MateriaHistorico temp = new MateriaHistorico();
      temp.setCodCurso("CI"+i);
      temp.setNomeAtivCurric("Nome"+i);
      temp.setMediaFinal("Media"+i);
      temp.setChTotal("CH"+i);
      toadd.add(temp);
      mat.add(toadd);
    }

    TelaEstado telaest = TelaEstado.getInstance();
    Vector<Materia> vet = new Vector<Materia>();
    for(int i = 0; i < 10; i++){
      Materia temp = new Materia();
      temp.setCodDisci("CI"+i);
      temp.setPeriodo(i+1);
      temp.setChTotal(i);
      temp.setNomeDisci("MateriaB"+i);
      vet.add(temp);
    }

    TelaSolicitar telasol = TelaSolicitar.getInstance();
    Vector<Materia> vet2 = new Vector<Materia>();
    for(int i = 0; i < 10; i++){
      Materia temp = new Materia();
      temp.setCodDisci("CI"+i);
      temp.setPeriodo(i+1);
      temp.setChTotal(i);
      temp.setNomeDisci("MateriaS"+i);
      vet2.add(temp);
    }

    tela.subscribe(new Test());

    telasol.setMateriasNaoCursadasOfertadas(vet2);

    telaest.setMateriasCursadas(mat);
    telaest.setMateriasBarreira(vet);
  }

  public void update(Tela t){
    if(t.getPedido() == "import"){
      TelaInicial tela = TelaInicial.getInstance();
      System.out.println("Importando de " + tela.getImportPath());
      //fazer sets e gets necessários
    }
  }
}
