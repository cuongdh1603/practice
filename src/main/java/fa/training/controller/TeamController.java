package fa.training.controller;

import fa.training.model.Team;
import fa.training.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
public class TeamController {


    @Autowired
    public TeamService teamService;

    @ModelAttribute
    public List<Team> teams() {
        return teamService.getAllTeams();
    }

    @GetMapping({"/", "/home"})
    public String homePage() {
        return "home";
    }

    @GetMapping("/team")
    public String managePage(Model model, @ModelAttribute List<Team> teams) {
        Team team = new Team();
        model.addAttribute("team", team);
        model.addAttribute("teams", teams);
        return "team_manage";
    }

    @PostMapping("/team/add")
    public String addTeam(Model model,
                          @Valid @ModelAttribute("team") Team team,
                          BindingResult result,
                          @ModelAttribute List<Team> teams,
                          RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            model.addAttribute("team", team);
            model.addAttribute("teams", teams);
            return "team_manage";
        }
        if(teams.size() < 16) {
            teamService.addTeam(team);
        }
        else {
            redirectAttributes.addFlashAttribute("error","There are 16 teams");
        }
        if(teamService.getSizeTeam() == 16){
            return "redirect:/team/group";
        }
        return "redirect:/team";
    }

    @GetMapping("/team/edit/{id}")
    public String updateForm(Model model,
                             @ModelAttribute List<Team> teams,
                             @PathVariable("id") Integer id)
    {
        Team team = teamService.getTeamById(id);
        model.addAttribute("team", team);
        model.addAttribute("teams", teams);
        return "update_team";
    }

    @PostMapping("/team/edit")
    public String updateForm(Model model,
                             @Valid @ModelAttribute("team") Team team,
                             BindingResult result,
                             @ModelAttribute List<Team> teams
    )
    {
        if(result.hasErrors()) {
            model.addAttribute("team", team);
            model.addAttribute("teams", teams);
            return "team_manage";
        }
        teamService.addTeam(team);

        return "redirect:/team";
    }

    @GetMapping("/team/delete/{id}")
    public String deleteForm(@PathVariable("id") Integer id) {
        if(teamService.getTeamById(id) != null) {
            teamService.deleteTeam(id);
        }
        return "redirect:/team";
    }

    @GetMapping("/team/group")
    public String groupForm(Model model,
                            @ModelAttribute List<Team> teams
    ) {
        model.addAttribute("teams", teams);
        if(teamService.getSizeTeam() == 16) {
            model.addAttribute("group", "_");
        }

        return "group";
    }

    @PostMapping("/team/group")
    public String doGroupForm(Model model,
                            @ModelAttribute List<Team> teams
    ) {
        model.addAttribute("teams", teams);
        model.addAttribute("group", "_");
        model.addAttribute("divide", "_");
        if(teamService.getSizeTeam() == 16) {
            List<Team> shuffleTeam = teamService.getAllTeams();
            Collections.shuffle(shuffleTeam);
            model.addAttribute("groupA", shuffleTeam.subList(0, 4));
            model.addAttribute("groupB", shuffleTeam.subList(4, 8));
            model.addAttribute("groupC", shuffleTeam.subList(8, 12));
            model.addAttribute("groupD", shuffleTeam.subList(12, shuffleTeam.size()));
        }
        return "group";
    }
}
