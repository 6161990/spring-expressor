package kim.yoon.ems.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="empinfo")
public class Empinfo {

    @Id
    private Integer empno;

    @Column
    private String empname;

    @Column
    private Integer teamno;
}
