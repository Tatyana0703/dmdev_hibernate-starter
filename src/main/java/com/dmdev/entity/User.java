package com.dmdev.entity;

//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
//import org.hibernate.annotations.TypeDef;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
//@TypeDef(name = "dmdev", typeClass = JsonBinaryType.class)
public class User {

    @Id
    private String username;
    private String firstname;
    private String lastname;

//    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_date")
    private Birthday birthDate;

    @Type(JsonBinaryType.class)
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;
}
