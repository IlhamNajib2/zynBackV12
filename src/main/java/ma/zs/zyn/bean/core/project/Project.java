package ma.zs.zyn.bean.core.project;

import java.util.Objects;
import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.bean.core.inscription.InscriptionMembre;
import ma.zs.zyn.bean.core.template.Template;
import ma.zs.zyn.bean.core.template.DomainTemplate;
import ma.zs.zyn.bean.core.template.ProjectTemplate;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="project_seq",sequenceName="project_seq",allocationSize=1, initialValue = 1)
public class Project  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String name;

    private LocalDateTime generatedDate ;


    private String yaml;

    private ProjectState projectState ;
    private InscriptionMembre inscriptionMembre ;
    private DomainTemplate domainTemplate ;

    private List<ProjectTemplate> projectTemplates ;

    public Project(){
        super();
    }

    public Project(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public Project(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="project_seq")
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
    public LocalDateTime getGeneratedDate(){
        return this.generatedDate;
    }
    public void setGeneratedDate(LocalDateTime generatedDate){
        this.generatedDate = generatedDate;
    }
    @Lob
    @Column(columnDefinition="TEXT")
    public String getYaml(){
        return this.yaml;
    }
    public void setYaml(String yaml){
        this.yaml = yaml;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_state")
    public ProjectState getProjectState(){
        return this.projectState;
    }
    public void setProjectState(ProjectState projectState){
        this.projectState = projectState;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscription_membre")
    public InscriptionMembre getInscriptionMembre(){
        return this.inscriptionMembre;
    }
    public void setInscriptionMembre(InscriptionMembre inscriptionMembre){
        this.inscriptionMembre = inscriptionMembre;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_template")
    public DomainTemplate getDomainTemplate(){
        return this.domainTemplate;
    }
    public void setDomainTemplate(DomainTemplate domainTemplate){
        this.domainTemplate = domainTemplate;
    }
    @OneToMany(mappedBy = "project")
    public List<ProjectTemplate> getProjectTemplates(){
        return this.projectTemplates;
    }

    public void setProjectTemplates(List<ProjectTemplate> projectTemplates){
        this.projectTemplates = projectTemplates;
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
        Project project = (Project) o;
        return id != null && id.equals(project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

