package pji.example.pji.implementation.Collection;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Comparator;

import pji.example.pji.implementation.CollectionBdd.LivreDaoImpl;

/**
 * Created by imane khemici on 01/04/15.
 */
@DatabaseTable(tableName = "livre", daoClass = LivreDaoImpl.class)
public class Livre implements Serializable{



    public Livre() {}
    public Livre(String titre, String auteur, String isbn, String genre) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.genre = genre;
    }

    /** Getter/Setter
     *
     */
    public Integer getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublie_le() {
        return publie_le;
    }

    public float getNote() {
        return note;
    }

    public float getNotePerso() {
        return notePerso;
    }

    public boolean isFavori() {
        return favori;
    }

    public boolean isPanier() {
        return panier;
    }

    public boolean isLu() {
        return lu;
    }

    public boolean isMappartient() {
        return mappartient;
    }

    public String getEmprunte() {
        return emprunte;
    }

    public String getPrete() {
        return prete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublie_le(String publie_le) {
        this.publie_le = publie_le;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public void setNotePerso(float notePerso) {
        this.notePerso = notePerso;
    }

    public void setPanier(boolean panier) {
        this.panier = panier;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    public void setMappartient(boolean mappartient) {
        this.mappartient = mappartient;
    }

    public void setEmprunte(String emprunte) {
        this.emprunte = emprunte;
    }

    public void setPrete(String prete) {
        this.prete = prete;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setImage(String url){
        this.image= url;
    }

    public String getImage(){
        return this.image;
    }


    public static final Comparator<Livre> TITRE = new Comparator<Livre>() {


        @Override
        public int compare(Livre arg0, Livre arg1) {
            // TODO Auto-generated method stub
            Livre p = (Livre) arg0;
            Livre q = (Livre) arg1;
            if (p.getTitre().equals(q.getTitre())) {
                return p.getAuteur().compareTo(q.getAuteur());
            } else return (p.getTitre().compareTo(q.getTitre()));

        }

    };

    public static final Comparator AUTEUR = new Comparator<Livre>() {

        @Override
        public int compare(Livre arg0, Livre arg1) {
            Livre p = (Livre) arg0;
            Livre q = (Livre) arg1;
            if (p.getAuteur().equals(q.getAuteur())) {
                return p.getGenre().compareTo(q.getGenre());
            } else return p.getAuteur().compareTo(q.getAuteur());
        }

    };

        public static final Comparator GENRE = new Comparator<Livre>() {

            @Override
            public int compare(Livre arg0,Livre arg1) {
                Livre p=(Livre) arg0;
                Livre q=(Livre) arg1;
                if(p.getGenre().equals( q.getGenre()))
                {
                    return p.getTitre().compareTo(q.getTitre());
                }  else return p.getGenre().compareTo(q.getGenre());
            }
        };
    /** A/D
     *
     */

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "titre", canBeNull = false)
    private String titre;
    @DatabaseField(columnName = "auteur", canBeNull = false)
    private String auteur;
    @DatabaseField(columnName = "isbn", canBeNull = false)
    private String isbn;
    @DatabaseField (columnName = "genre", canBeNull = false)
    private String genre;
    @DatabaseField (columnName = "publie", canBeNull = true)
    private String publie_le;
    @DatabaseField (columnName = "note", canBeNull = true)
    private float note;
    @DatabaseField (columnName = "notePerso", canBeNull = true)
    private float notePerso;
    @DatabaseField (columnName = "favori", canBeNull = true)
    private boolean favori;
    @DatabaseField (columnName = "panier",canBeNull = true)
     private  boolean panier;
    @DatabaseField (columnName = "lu", canBeNull = true)
    private boolean lu;
    @DatabaseField (columnName = "mappartient", canBeNull = true)
    private boolean mappartient;
    @DatabaseField (columnName = "emprunte", canBeNull = true)
    private String emprunte;
    @DatabaseField (columnName = "prete", canBeNull = true)
    private String prete;
    @DatabaseField (columnName = "langue", canBeNull = true)
    private String langue;
    //elle va contenir l'url de l'image
    @DatabaseField (columnName = "image", canBeNull = true)
    private String image;

}
