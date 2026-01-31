package com.API_MENTEC_SPRINGBOOT.Model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="tb_role")
@Getter
@Setter
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String name;



    public enum Values{
        ESTUDANTEFATEC(2L),
        BASICO(1L),
        PROFESSOR(3L),
        ADMIN(4L);

        Long roleId;

        Values(Long roleId){
            this.roleId = roleId;
        }
    }
}
