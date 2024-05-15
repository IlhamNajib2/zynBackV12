package ma.zs.zyn.bean.core.project;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "paiment_collaborator_state")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="paiment_collaborator_state_seq",sequenceName="paiment_collaborator_state_seq",allocationSize=1, initialValue = 1)
public class PaimentCollaboratorState  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public PaimentCollaboratorState(){
        super();
    }

    public PaimentCollaboratorState(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public PaimentCollaboratorState(String code){
        this.code = code ;
    }
    public PaimentCollaboratorState(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="paiment_collaborator_state_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
        label = code;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaimentCollaboratorState paimentCollaboratorState = (PaimentCollaboratorState) o;
        return id != null && id.equals(paimentCollaboratorState.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

