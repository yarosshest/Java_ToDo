package com.MIREA.ToDo.components;

import com.MIREA.ToDo.entity.Role;
import com.MIREA.ToDo.entity.User;
import com.MIREA.ToDo.repository.RoleRepository;
import com.MIREA.ToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;
import data.ScheduleTeam;
import data.ScheduleCourse;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setUsername("Test@T");
        user.setPassword("Test");
        user.setUniversity("Test");
        user.setGroup("Test");
        user.setRoles(Set.of(adminRole));
        userRepository.save(user);
//        createSchedules();
        alreadySetup = true;
    }

    @Transactional
    void createSchedules() {
        ScheduleTeam obj = new ScheduleTeam(
                new ScheduleCourse("IIT", "2", "https://webservices.mirea.ru/upload/iblock/6c9/jifmf42ev2vi0oa03d1vddyaqvrrauni/IIT_2-kurs_22_23_osen_07.10.2022.xlsx")
                , "ИКБО-06-21"
        );

    }

    @Transactional
    Role createRoleIfNotFound(String name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}