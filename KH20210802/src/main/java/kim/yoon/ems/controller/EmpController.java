package kim.yoon.ems.controller;

import kim.yoon.ems.entity.Empinfo;
import kim.yoon.ems.entity.EmpinfoForm;
import kim.yoon.ems.repository.EmpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class EmpController {

    @Autowired
    EmpRepository empRep;

    @RequestMapping(path="/findAll")
    public String showEmpinfoList(Model model) {
        model.addAttribute("empinfo", empRep.findAll());
        return "emp/emplist";
    }

    @RequestMapping(path="/findByTeamno/{teamno}")
    public String showEmpinfoListByTeamno(@PathVariable int teamno, Model model) {

        System.out.println(empRep.findByTeamno(teamno));

        model.addAttribute("empinfo", empRep.findByTeamno(teamno));
        return "emp/emplist";

    }

    @RequestMapping(path = "getOne/{empno}")
    public String showEmpinfo(@PathVariable int empno, Model model) {

        model.addAttribute("empinfo", empRep.findById(empno));
        return "emp/emplist";
    }

    @RequestMapping(path = "/finyByEmpnameAndTeamno/{empname}/{teamno}")
    public String showEmpinfoListByNameAndTeamno(@PathVariable String empname, @PathVariable int teamno, Model model) {

        model.addAttribute("empinfo", empRep.findByEmpnameAndTeamno(empname, teamno));
        return "emp/emplist";

    }

    @RequestMapping(path = "/findByEmpnameLike/{empname}")
    public String showEmpinfoListNameLike(@PathVariable String empname, Model model) {

        model.addAttribute("empinfo", empRep.findByEmpnameLike("%"+ empname + "%"));
        return "emp/emplist";

    }

    @RequestMapping(path = "/create/input")
    public String createInput() {
        return "emp/createinput";
    }

    @RequestMapping(path="/create/complate", method= RequestMethod.POST)
    public String createComplate(EmpinfoForm form) {

        Empinfo emp = new Empinfo();
        emp.setEmpno(form.getEmpno());
        emp.setEmpname(form.getEmpname());
        emp.setTeamno(form.getTeamno());
        empRep.save(emp);

        return "redirect:/findAll";

    }

    @PostMapping("/update/complate/{empno}")
    public String updateComplate(@PathVariable int empno, EmpinfoForm form){
        Empinfo emp = empRep.getById(empno);
        emp.setEmpno(form.getEmpno());
        emp.setEmpname(form.getEmpname());
        emp.setTeamno(form.getTeamno());
        empRep.save(emp);
        return "";
    }


}
