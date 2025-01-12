package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name= "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

//    @JsonIgnore
    @OneToMany(mappedBy = "member") //order 클래스(테이블)에 있는 member 필드에 맵핑
    private List<Order> orders = new ArrayList<>();


}
