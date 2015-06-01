package pji.example.pji.implementation.CollectionBdd;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import pji.example.pji.implementation.Collection.Livre;

/**
 * Created by imane khemici on 08/04/15.
 */
public interface LivreDao extends Dao<Livre, Integer> {

    public List findAll() ;

    public List findAllPanier();

    public List findAllFavoris();

    public List findAllNonLus();

    public List findAllLus();

    public List findAllThisGenre(String genre);

    public int addData(Livre livre)throws SQLException;

    public List findByTitle(String titre) throws SQLException;

    public List findByIsbn(String titre);

    public List findByAuteur(String titre);

    public List findByGenre(String titre);

    public List findByLangue(String titre);
}
