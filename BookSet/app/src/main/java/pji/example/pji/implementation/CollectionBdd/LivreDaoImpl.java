package pji.example.pji.implementation.CollectionBdd;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pji.example.pji.implementation.Collection.Livre;

/**
 * Created by imane khemici on 08/04/15.
 */
public class LivreDaoImpl extends BaseDaoImpl<Livre,Integer> implements  LivreDao{


 public LivreDaoImpl(ConnectionSource connectionSource)
            throws SQLException {

     super(connectionSource, Livre.class);
    }
    //Tout
    public List findAll() {
        List livres = new ArrayList<>();
        try {
            livres = queryForAll();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }
    //Par titre
    public List<Livre> findByTitle(String titre) throws SQLException {

        PreparedQuery<Livre> requete = null;
        List<Livre> result = new ArrayList<Livre>();
        try {
            requete = queryBuilder().where().like("titre","%"+titre+"%").prepare();
            result = query(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
    public Livre findByTitre(String titre) throws SQLException {

        PreparedQuery<Livre> requete = null;
        List<Livre> result = new ArrayList<Livre>();
        Livre livre= new Livre();
        try {
            requete = queryBuilder().where().eq("titre",titre).prepare();
            result = query(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Livre livreA : result){
            if(titre.equals(livreA.getTitre())){
                livre = livreA;
            }
        }
        return livre;
    }
    //Isbn
    public List findByIsbn(String isbn) {

        PreparedQuery<Livre> requete = null;
        List result = new ArrayList();

        try {
            requete = queryBuilder().where().like("isbn","%"+isbn+"%").prepare();
            result = query(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
    //Auteur
    public List findByAuteur(String auteur){
         PreparedQuery<Livre> requete = null;
        List result = new ArrayList();

        try {
            requete = queryBuilder().where().like("auteur","%"+auteur+"%").prepare();
            result = query(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public List findByGenre(String genre) {
        PreparedQuery<Livre> requete = null;
        List result = new ArrayList();

        try {
            requete = queryBuilder().where().like("genre","%"+genre+"%").prepare();
            result = query(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List findByLangue(String langue) {
       PreparedQuery<Livre> requete = null;
        List result = new ArrayList();

        try {
            requete = queryBuilder().where().like("langue","%"+langue+"%").prepare();
            result = query(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public List findAllPanier(){
        List<Livre> resultat = new ArrayList();
        List<Livre> all = new ArrayList();
        all =  findAll();
        for(Livre livre1 : all){
            if(livre1.isPanier() == true){
                resultat.add(livre1);
            }

        }
        return resultat;

    }

    public List findAllFavoris(){

        List<Livre> resultat = new ArrayList();
        List<Livre> all = new ArrayList();
        all =  findAll();
        for(Livre livre1 : all){
            if(livre1.isFavori() == true){
                resultat.add(livre1);
            }

        }
        return resultat;
    }

    public List findAllThisGenre(String genre){

        List<Livre> resultat = new ArrayList();
        List<Livre> all = new ArrayList();
        all =  findAll();
        for(Livre livre1 : all){
            if(livre1.getGenre().equals(genre)){
                resultat.add(livre1);
            }

        }
        return resultat;
    }


    public List findAllNonLus(){
        List<Livre> resultat = new ArrayList();
        List<Livre> all = new ArrayList();
        all =  findAll();
        for(Livre livre1 : all){
            if(livre1.isLu() == false){
                resultat.add(livre1);
            }

        }
        return resultat;
    }

    public List findAllLus(){
        List<Livre> resultat = new ArrayList();
        List<Livre> all = new ArrayList();
        all =  findAll();
        for(Livre livre1 : all){
            if(livre1.isLu() == true){
                resultat.add(livre1);
            }

        }
        return resultat;
    }

    public boolean existe(String titre) throws SQLException {
        List<Livre> all = queryForAll();
        boolean existe = false;
        for(Livre livre1 : all){
            if(livre1.getTitre().equals(titre)){
                existe = true;
            }

        }
        return existe;

    }
    public int addData(Livre livre) throws SQLException {

        return this.create(livre);
    }
    public int delete(Livre livre) throws SQLException {

        return this.deleteById(livre.getId());
    }

    public void updateLivre(Livre livre) throws SQLException {

       this.update(livre);
    }
}
