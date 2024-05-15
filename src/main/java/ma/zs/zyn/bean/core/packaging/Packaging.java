package ma.zs.zyn.bean.core.packaging;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.zyn.bean.core.category.CategoryPackaging;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "packaging")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="packaging_seq",sequenceName="packaging_seq",allocationSize=1, initialValue = 1)
public class Packaging  extends BaseEntity     {

    private Long id;



    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String description;

    private LocalDateTime dateStart ;

    private LocalDateTime dateEnd ;

    private BigDecimal price = BigDecimal.ZERO;

    private BigDecimal maxEntity = BigDecimal.ZERO;

    private BigDecimal maxProjet = BigDecimal.ZERO;

    private BigDecimal maxAttribut = BigDecimal.ZERO;

    private BigDecimal maxIndicator = BigDecimal.ZERO;

    private CategoryPackaging categoryPackaging ;


    public Packaging(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="packaging_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public LocalDateTime getDateStart(){
        return this.dateStart;
    }
    public void setDateStart(LocalDateTime dateStart){
        this.dateStart = dateStart;
    }
    public LocalDateTime getDateEnd(){
        return this.dateEnd;
    }
    public void setDateEnd(LocalDateTime dateEnd){
        this.dateEnd = dateEnd;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    public BigDecimal getMaxEntity(){
        return this.maxEntity;
    }
    public void setMaxEntity(BigDecimal maxEntity){
        this.maxEntity = maxEntity;
    }
    public BigDecimal getMaxProjet(){
        return this.maxProjet;
    }
    public void setMaxProjet(BigDecimal maxProjet){
        this.maxProjet = maxProjet;
    }
    public BigDecimal getMaxAttribut(){
        return this.maxAttribut;
    }
    public void setMaxAttribut(BigDecimal maxAttribut){
        this.maxAttribut = maxAttribut;
    }
    public BigDecimal getMaxIndicator(){
        return this.maxIndicator;
    }
    public void setMaxIndicator(BigDecimal maxIndicator){
        this.maxIndicator = maxIndicator;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_packaging")
    public CategoryPackaging getCategoryPackaging(){
        return this.categoryPackaging;
    }
    public void setCategoryPackaging(CategoryPackaging categoryPackaging){
        this.categoryPackaging = categoryPackaging;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packaging packaging = (Packaging) o;
        return id != null && id.equals(packaging.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

