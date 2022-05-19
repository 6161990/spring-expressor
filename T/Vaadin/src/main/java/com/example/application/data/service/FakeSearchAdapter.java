package com.example.application.data.service;

import com.example.application.data.views.*;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FakeSearchAdapter implements SearchAdapter {

    static final List<User> users = Lists.newArrayList();
    static final List<Membership> memberships = Lists.newArrayList();

    static {

        Service service1 = Service.builder().id("1").name("SPACE_RENTAL").build();
        Service service2 = Service.builder().id("11").name("OUT_GOING").build();
        Service service3 = Service.builder().id("2").name("OUT_GOING").build();
        Service service4 = Service.builder().id("2").name("EVENT").build();

        MembershipInfo membershipInfo = MembershipInfo.builder().id("membershipId").name("냥냥").build();
        Ticket ticket = Ticket.builder().service(service1).membershipInfo(membershipInfo).reason("reason").state("AVAILABLE").createdAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").build();
        Ticket ticket2 = Ticket.builder().service(service2).membershipInfo(membershipInfo).reason("reason").state("AVAILABLE").createdAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").build();
        Ticket ticket3 = Ticket.builder().service(service3).membershipInfo(membershipInfo).reason("reason").state("AVAILABLE").createdAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").build();
        Ticket ticket4 = Ticket.builder().service(service4).membershipInfo(membershipInfo).reason("reason").state("AVAILABLE").createdAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").build();

        List<Ticket> tickets = Lists.newArrayList();
        tickets.add(ticket);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);

        User user1 = User.builder().id("f9c11039").name("최지욱").phoneNumber("01022222222").joinedAt("2022-02-02T02:03:02").isInactive("false").isBlock("false").deletedAt(null).tickets(tickets).memberships(memberships).build();

        users.add(user1);

    }



    static {
        ServiceRunningContext serviceRunningContext = ServiceRunningContext.builder().serviceName("독서모임1").impossibleTerminatedAt("2022-02-02T02:03:02").state("AVAILABLE").createdAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").servicePeriod("2022-02-02T02:03:02~2022-02-02T02:03:02").build();
        ServiceRunningContext serviceRunningContext1 = ServiceRunningContext.builder().serviceName("독서모임2").impossibleTerminatedAt("2022-02-02T02:03:02").state("AVAILABLE").createdAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").servicePeriod("2022-02-02T02:03:02~2022-02-02T02:03:02").build();
        ServiceRunningContext serviceRunningContext2 = ServiceRunningContext.builder().serviceName("독서모임3").impossibleTerminatedAt("2022-02-02T02:03:02").state("AVAILABLE").createdAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").servicePeriod("2022-02-02T02:03:02~2022-02-02T02:03:02").build();

        MembershipInfo membershipInfo = MembershipInfo.builder().id("membershipId").name("냥냥").build();

        Membership membership = Membership.builder()
                .membershipInfo(membershipInfo)
                .periodOfMembership("2022-02-02T02:03:02~2022-02-02T02:03:02")
                .serviceRunningContexts(Lists.newArrayList(serviceRunningContext, serviceRunningContext2, serviceRunningContext1))
                .build();

        memberships.add(membership);

    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public List<User> searchUserByUserId(String userId) {
        return users.stream().filter(u -> u.getId() == userId).collect(Collectors.toList());
    }

    @Override
    public List<User> searchUserByPhoneNumber(String phoneNumber) {
        return users.stream().filter(u -> u.getPhoneNumber() == phoneNumber).collect(Collectors.toList());
    }

    @Override
    public List<User> searchUserByUserName(String userName) {
        return users.stream().filter(u -> u.getName() == userName).collect(Collectors.toList());
    }
}
