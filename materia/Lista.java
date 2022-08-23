package materia;

import java.io.Serializable;
import java.util.Vector;

interface Iterator<T>{
  boolean hasNext();
  T next();
}

public class Lista<T extends Serializable> implements Iterator<T>{

  private Vector<T> lista;
  private int atual = -1;

  public Lista(){
    lista = new Vector<T>();
    atual = 0;
  }

  public void insere(T elemento){
    lista.add(elemento);
  }

  public void insere(int index, T elemento){
    lista.add(index, elemento);
  }

  public boolean remove(T elemento){
    return lista.remove(elemento);
  }

  public boolean hasNext(){
    if(atual < 0 || lista.size() <= atual) return false;
    return true;
  }

  public T next(){
    if(atual >= lista.size()) { return null; }
    T temp = lista.get(atual++);
    return temp;
  }

  public Lista<T> Iterator(){
    atual = 0;
    if(lista.size() == 0) atual = -1;
    return this;
  }
}
