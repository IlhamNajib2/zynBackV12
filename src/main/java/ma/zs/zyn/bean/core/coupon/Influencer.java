package ma.zs.zyn.bean.core.coupon;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zs.zyn.zynerator.security.bean.User;

@Entity
@Table(name = "influencer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="influencer_seq",sequenceName="influencer_seq",allocationSize=1, initialValue = 1)
public class Influencer  extends User    {


    public Influencer(String username) {
        super(username);
    }


    @Column(length = 500)
    private String nickName;

    @Column(length = 500)
    private String rib;










    public Influencer(){
        super();
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="influencer_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNickName(){
        return this.nickName;
    }
    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    public String getRib(){
        return this.rib;
    }
    public void setRib(String rib){
        this.rib = rib;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Influencer influencer = (Influencer) o;
        return id != null && id.equals(influencer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

