package cl.accenture.concrete.desafiojava.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Integer id;

    @JsonProperty
    @Column(name="name")
    private String name;

    @JsonProperty
    @Column(name="email")
    private String email;


    @Column(name = "password")
    private String password;


    @Column(name = "ultimo_login")
    private String ultimo_login;

    @OneToMany
    private List<Telefono> telefonos;

    public Usuario (){

    }

    public Usuario(String name, String email, List<Telefono> Telefonos, String password ){
        this.name=name;
        this.email=email;
        this.password=encriptar(password);
        this.ultimo_login=null;
        this.ultimo_login=dateToDate(new Date())+""+dateToTime(new Date());
        this.telefonos = new List<Telefono>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Telefono> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Telefono telefono) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Telefono> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Telefono> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Telefono get(int index) {
                return null;
            }

            @Override
            public Telefono set(int index, Telefono element) {
                return null;
            }

            @Override
            public void add(int index, Telefono element) {

            }

            @Override
            public Telefono remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Telefono> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Telefono> listIterator(int index) {
                return null;
            }

            @Override
            public List<Telefono> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }

    public Usuario(String name, String email, Date ultimo_login, Telefono Telefonos, String password ){
        this.name=name;
        this.email=email;
        this.password=encriptar(password);
        this.ultimo_login=dateToDate(new Date())+" "+dateToTime(new Date());
        this.telefonos = new List<Telefono>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Telefono> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Telefono telefono) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Telefono> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Telefono> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Telefono get(int index) {
                return null;
            }

            @Override
            public Telefono set(int index, Telefono element) {
                return null;
            }

            @Override
            public void add(int index, Telefono element) {

            }

            @Override
            public Telefono remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Telefono> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Telefono> listIterator(int index) {
                return null;
            }

            @Override
            public List<Telefono> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }
    public Usuario(String email, List<Telefono> telefonos){
        this.email=email;
        this.telefonos = new List<Telefono>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Telefono> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Telefono telefono) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Telefono> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Telefono> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Telefono get(int index) {
                return null;
            }

            @Override
            public Telefono set(int index, Telefono element) {
                return null;
            }

            @Override
            public void add(int index, Telefono element) {

            }

            @Override
            public Telefono remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Telefono> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Telefono> listIterator(int index) {
                return null;
            }

            @Override
            public List<Telefono> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }

    public Usuario(String email, String password){
        this.email=email;
        this.password=encriptar(password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUltimo_login() {
        return ultimo_login;
    }

    public void setUltimo_login(String ultimo_login) {
        this.ultimo_login = ultimo_login;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public String dateToTime(Date date){
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        return formatoHora.format(date);
    }

    public String dateToDate(Date date){
        DateFormat formatoFecha =new SimpleDateFormat("yyyy-MM-dd");
        return formatoFecha.format(date);
    }
    public String encriptar(String password) {

        String result;
        result = DigestUtils.md5Hex(password);
        return result;

    }

    @Override
    public String toString(){
        return "Usuario: [id: "+id+", name: "+name+", email: "+email+"password: "+password+ ", Phones: "+telefonos+"]";
    }
}
