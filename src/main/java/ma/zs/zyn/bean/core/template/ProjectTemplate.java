package ma.zs.zyn.bean.core.template;

import java.util.Objects;





import ma.zs.zyn.bean.core.project.Project;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_template")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_template_seq",sequenceName="project_template_seq",allocationSize=1, initialValue = 1)
public class ProjectTemplate  extends BaseEntity     {

    private Long id;



    private Template template ;
    private Project project ;


    public ProjectTemplate(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_template_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template")
    public Template getTemplate(){
        return this.template;
    }
    public void setTemplate(Template template){
        this.template = template;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
    public Project getProject(){
        return this.project;
    }
    public void setProject(Project project){
        this.project = project;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTemplate projectTemplate = (ProjectTemplate) o;
        return id != null && id.equals(projectTemplate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

