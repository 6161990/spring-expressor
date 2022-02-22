package kim.yoon.ems.repository;


import kim.yoon.ems.entity.Empinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepository extends JpaRepository<Empinfo, Integer> {

    List<Empinfo> findByTeamno(Integer teamno);

    List<Empinfo> findByEmpnameAndTeamno(String empname, Integer teamno);

    List<Empinfo> findByEmpnameLike(String empname);
}
