package dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet  implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    //автоинкремент
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //доп параметры: имя уникально и неизменно при работе
    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "pswd", unique = true, updatable = false)
    private String pswd;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(long id, String name, String pwd) {
        this.setId(id);
        this.setName(name);
        this.setPswd(pwd);
    }

    public UsersDataSet(String name, String pwd) {
        this.setId(-1);
        this.setName(name);
        this.setPswd(pwd);
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pswd='" + pswd + '\'' +
                '}';
    }
}
