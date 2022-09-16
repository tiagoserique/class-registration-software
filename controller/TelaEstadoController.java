package controller;


import view.TelaEstado;
import view.TelaSub;
import view.Tela;



public class TelaEstadoController extends Controller implements TelaSub{
    private TelaEstado screen;

    public TelaEstadoController(){
        this.screen = TelaEstado.getInstance();
        this.screen.subscribe(this);
    }

    public void update(Tela view){
        
    }
}
