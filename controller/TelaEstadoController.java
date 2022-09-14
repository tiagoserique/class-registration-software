package controller;

import java.util.*;

import view.TelaEstado;
import view.TelaSub;
import view.Tela;

import materia.Materia;
import materia.MateriaHistorico;

import model.HistoricoParser;
import model.MateriaParser;

public class TelaEstadoController extends Controller implements TelaSub{
    private TelaEstado screen;
    private HistoricoParser parserHistorico;
    private MateriaParser parserMateria;

    public TelaEstadoController(){
        this.screen = TelaEstado.getInstance();
        this.screen.subscribe(this);
        this.parserHistorico = HistoricoParser.getInstance();
        this.parserMateria = MateriaParser.getInstance();
    }

    public void update(Tela view){
        
    }
}
