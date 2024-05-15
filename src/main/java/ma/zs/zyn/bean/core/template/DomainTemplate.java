package ma.zs.zyn.bean.core.template;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "domain_template")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="domain_template_seq",sequenceName="domain_template_seq",allocationSize=1, initialValue = 1)
public class DomainTemplate  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String name;



    public DomainTemplate(){
        super();
    }

    public DomainTemplate(Long id,String name){
        this.id = id;
        this.name = name ;
    }
    public DomainTemplate(String name){
        this.name = name ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="domain_template_seq")
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

    @Transient
    public String getLabel() {
        label = name;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainTemplate domainTemplate = (DomainTemplate) o;
        return id != null && id.equals(domainTemplate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

