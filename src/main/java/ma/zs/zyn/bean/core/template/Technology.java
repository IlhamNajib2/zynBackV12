package ma.zs.zyn.bean.core.template;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "technology")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="technology_seq",sequenceName="technology_seq",allocationSize=1, initialValue = 1)
public class Technology  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String logo;

    private TypeTemplate typeTemplate ;


    public Technology(){
        super();
    }

    public Technology(Long id,String name){
        this.id = id;
        this.name = name ;
    }
    public Technology(String name){
        this.name = name ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="technology_seq")
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
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getLogo(){
        return this.logo;
    }
    public void setLogo(String logo){
        this.logo = logo;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_template")
    public TypeTemplate getTypeTemplate(){
        return this.typeTemplate;
    }
    public void setTypeTemplate(TypeTemplate typeTemplate){
        this.typeTemplate = typeTemplate;
    }

    @Transient
    public String getLabel() {
        label = name;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technology technology = (Technology) o;
        return id != null && id.equals(technology.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

