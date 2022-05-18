package com.example.demo.service;

import com.example.demo.dto.Service;
import com.example.demo.dto.Membership;
import com.example.demo.dto.User;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FakeSearchAdapter  {

    static final List<User> users = Lists.newArrayList();

    static {
        User user1 = User.builder().id("dsg1b242d-323dba").name("구자경").build();

        users.add(user1);

    }


    static final List<Membership> memberships = Lists.newArrayList();

    static {
        Service service1 = Service.builder().id("123").name("RENTAL").build();
        Service service2 = Service.builder().id("456").name("OUT_GOING").build();
        Service service3 = Service.builder().id("789").name("IN_GOING").build();
        Service service4 = Service.builder().id("101112").name("EVENT").build();
        Membership membership = Membership.builder()
                .id("2342355")
                .userId("dsg1b242d-323dba")
                .services(
                        Arrays.asList(service1, service2, service3, service4)
                )
                .build();

        memberships.add(membership);


    }

    public List<User> findAll() {
        return null;
    }

    public List<User> searchUserByUserId(String userId) {
        return users.stream().filter(u -> u.getId() == userId).collect(Collectors.toList());
    }

    public List<User> searchUserByMembershipId(String membershipId) {
        return users.stream().filter(u -> u.hasMembership(membershipId)).collect(Collectors.toList());
    }

    public List<Membership> searchMemberByUserId(String userId) {
        return memberships.stream().filter(m -> m.getUserId() == userId).collect(Collectors.toList());
    }

    public List<Membership> searchMemberByMembershipId(String membershipId) {
        return memberships.stream().filter(m -> m.getId() == membershipId).collect(Collectors.toList());
    }
}
