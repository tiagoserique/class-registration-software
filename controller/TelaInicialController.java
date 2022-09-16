package controller;

import java.util.*;

import view.*;

import materia.Materia;
import materia.MateriaHistorico;
import java.io.File;

import model.HistoricoParser;
import model.MateriaParser;

public class TelaInicialController extends Controller implements TelaSub {
    private TelaInicial screen;
    private HistoricoParser parserHistorico;
    private MateriaParser parserMateria;

    public static void main(String[] args){
        new TelaInicialController();
        new TelaEstadoController();
        new TelaSolicitarController();
    }

    public TelaInicialController(){
        this.screen = TelaInicial.getInstance();
        this.screen.subscribe(this);
        this.screen.setVisible(true);
        this.parserHistorico = HistoricoParser.getInstance();
        this.parserMateria = MateriaParser.getInstance();
    }

    public void update(Tela view){
        if (view.getPedido() == "import"){
            String path = this.screen.getImportPath();

            this.parserHistorico.setHistoricFile(path);
            System.out.println("Importando de " + path);


            this.screen = (TelaInicial) view;


            // coisas da tela de estado
            // =====================================================================
            // materias que ja foram cursadas de acor0do com o periodo que foram 
            // cursadas pelo aluno
            // armazena as materias que faltam cursar para a barreira
            Vector<Materia> matBarreira;      
            Vector<Materia> matNaoCursadasBarreira;

            // buscar da model que le todas as Materias da grade
            Vector<Vector<Materia>> materiasOfertadas = parserMateria.parseMaterias();
            Vector<Vector<MateriaHistorico>> matCursadas = parserHistorico.parseHistorico();

            matBarreira = filtraMateriasBarreira(materiasOfertadas);
            matNaoCursadasBarreira = filtraMateriasNaoCursadas(matBarreira, matCursadas);

            TelaEstado.getInstance().setMateriasBarreira(matNaoCursadasBarreira);
            TelaEstado.getInstance().setMateriasCursadas(matCursadas);

            // Mostrar dados de aprovação do último período (% de aprovação e 
            // quantas matérias reprovou por falta).
            
            // pega lista de materias do ultimo periodo
            Vector<MateriaHistorico> matUltimoPeriodo;

            if ( matCursadas.get(matCursadas.size() - 1).get(0).getSituacaoItem().equals(MATRICULA) )
                matUltimoPeriodo = matCursadas.get(matCursadas.size() - 2);
            else 
                matUltimoPeriodo = matCursadas.get(matCursadas.size() - 1);
            
            float ira = calculaIra(matCursadas)/100;
            Double porcentAprovacao = calculaPorcentAprovacao(matUltimoPeriodo); 
            int quantidadeReprovacao = calculaQuantidadeTipo(matUltimoPeriodo, REP_FALTA); 
            
            TelaEstado.getInstance().setIra(ira);
            TelaEstado.getInstance().setPorcentAprovacao(porcentAprovacao);
            TelaEstado.getInstance().setQuantidadeReprovacaoFalta(quantidadeReprovacao);


            // coisas da tela de solicitar
            // =====================================================================

            // Mostrar matérias ofertadas neste semestre que não foram cursadas, por ordem de período.

            // ler materias que foram cursadas e que estao no historico
            matCursadas = parserHistorico.parseHistorico();

            // ler materias que foram ofertadas
            Vector<Vector<Materia>> matOfertadas = parserMateria.parseMaterias();

            // filtra materias que nao foram cursadas
            Vector<Materia> matNaoCursadasOfertadas = filtraMateriasNaoCursadasSolicitar(matOfertadas, matCursadas);

            // mostra materias que nao foram cursadas
            TelaSolicitar.getInstance().setMateriasNaoCursadasOfertadas(matNaoCursadasOfertadas);

            // mostra quantidade sugeridas de materias
            int quantSugerido = calculaMateriaSugeridas(ira, porcentAprovacao);
            
            TelaSolicitar.getInstance().setQuantSugerido(quantSugerido);
        }
    }



    // funcoes da tela de estado ============================================================

    //  3 = ultimo periodo da barreira 
    // mO = materias ofertadas
    private Vector<Materia> filtraMateriasBarreira(Vector<Vector<Materia>> mO){
        Vector<Materia> matBarreira = new Vector<Materia>(); 

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < mO.get(i).size(); j++){
                matBarreira.add(mO.get(i).get(j));
            }
        }

        return matBarreira;
    }


    // pega as materias ofertadas e que ainda nao foram cursadas
    // mB = materias barreira | mC = materias cursadas
    private Vector<Materia> filtraMateriasNaoCursadas(Vector<Materia> mB, 
    Vector<Vector<MateriaHistorico>> mC){ 
        Vector<Materia> matNaoCursadas = new Vector<Materia>();

        for (int materia = 0; materia < mB.size(); materia++){
            String codBarreira = mB.get(materia).getCodDisci();

            Boolean condition = jaFoiCursada(mC, codBarreira);

            if ( !condition ){ 
                matNaoCursadas.add(mB.get(materia));
            }

        }

        return matNaoCursadas;
    }


    // funcoes da tela de solicitar ============================================================

    private Vector<Materia> filtraMateriasNaoCursadasSolicitar(Vector<Vector<Materia>> mO, Vector<Vector<MateriaHistorico>> mC){ 
        Vector<Materia> matNaoCursadas = new Vector<Materia>();

        for (int periodo = 0; periodo < mO.size(); periodo++){
            for (int materia = 0; materia < mO.get(periodo).size(); materia++){
                
                String codBarreira = mO.get(periodo).get(materia).getCodDisci();

                Boolean condition = jaFoiCursada(mC, codBarreira);

                if ( !condition ){ 
                    matNaoCursadas.add(mO.get(periodo).get(materia));
                }
            }
        }

        return matNaoCursadas;
    }
}
