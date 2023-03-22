package it.academy.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class AppUser implements Serializable {

    @Id
    @Column(name = "app_user_id") //можно колонку просто id обозвать
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private AppUserRole appUserRole;

    //это ппц, конечно. Если ты используешь ломбок, то пусть он и генерит тебе все эти методы замени это на ломбок, пожалуй.
    //но перед соебсом почитай, как он работает и будь готова ответить, каким хером он под капотом занимается. там не очень сложно
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppUser appUser = (AppUser) o;

        if (id != null ? !id.equals(appUser.id) : appUser.id != null) return false;
        if (lastName != null ? !lastName.equals(appUser.lastName) : appUser.lastName != null) return false;
        if (firstName != null ? !firstName.equals(appUser.firstName) : appUser.firstName != null) return false;
        if (surname != null ? !surname.equals(appUser.surname) : appUser.surname != null) return false;
        return email != null ? email.equals(appUser.email) : appUser.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", appUserRole=" + appUserRole.getRoleName() +
                '}';
    }
}
