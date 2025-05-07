package it.trainingsi2001.rentalcars.service;

import java.util.List;

public interface BaseService<T> {

    public List<T> listAll();

    public T caricaSingoloElemento(Long id);

    public T aggiorna(T obj);

    public T inserisciNuovo(T obj);

    public void rimuovi(Long idToRemove);

}
