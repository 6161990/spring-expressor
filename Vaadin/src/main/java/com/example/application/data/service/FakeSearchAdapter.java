package com.example.application.data.service;

import com.example.application.data.views.*;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("poc")
public class FakeSearchAdapter implements SearchAdapter {

    static final List<User> users = Lists.newArrayList();
    static final List<Membership> memberships = Lists.newArrayList();

    static {

        Service service1 = Service.builder().id("6854475101572108288").serviceName("SPACE_RENTAL").build();
        Service service2 = Service.builder().id("6854476067744305152").serviceName("FREE_OUT_GOING").build();
        Service service3 = Service.builder().id("6856595835385712640").serviceName("PAY_OUT_GOING").build();
        Service service4 = Service.builder().id("6854476091931168768").serviceName("EVENT").build();

        MembershipInfo membershipInfo = MembershipInfo.builder().id("membershipId").name("클럽-냥냥").build();
        Ticket ticket = Ticket.builder().service(service1).membershipInfo(membershipInfo).reason("reason").state("AVAILABLE").createdAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").build();
        Ticket ticket2 = Ticket.builder().service(service2).membershipInfo(membershipInfo).reason("reason").state("AVAILABLE").createdAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").build();
        Ticket ticket3 = Ticket.builder().service(service3).membershipInfo(membershipInfo).reason("reason").state("AVAILABLE").createdAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").build();
        Ticket ticket4 = Ticket.builder().service(service4).membershipInfo(membershipInfo).reason("reason").state("AVAILABLE").createdAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").build();

        Wallet wallet = new Wallet();
        List<Ticket> tickets = Lists.newArrayList();
        wallet.setTicketHistory(tickets);
        tickets.add(ticket);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);

        User user1 = User.builder().userId("f9c11039-50f8-4f7a-8759-574eb2ea1933").userName("최지욱").email("goen2312@gmail.com").phoneNumber("01022222222").signedAt("2022-05-02T02:03:02").isInactive("false").isBlock("false").deletedAt(null).wallet(wallet).memberships(memberships).build();
        User user2 = User.builder().userId("574eb223-2034-df32").userName("손석구").email("goen2312@gmail.com").phoneNumber("01054019292").signedAt("2022-02-02T02:03:02").isInactive("false").isBlock("false").deletedAt(null).wallet(wallet).memberships(memberships).build();

        users.add(user1);
        users.add(user2);

    }

    static {
        ServiceRunningContext serviceRunningContext = ServiceRunningContext.builder().serviceName("독서모임1").impossibleTerminatedAt("2022-02-02T02:03:02").state("AVAILABLE").createdAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").servicePeriod("2022-02-02T02:03:02~2022-02-02T02:03:02").build();
        ServiceRunningContext serviceRunningContext1 = ServiceRunningContext.builder().serviceName("독서모임2").impossibleTerminatedAt("2022-02-02T02:03:02").state("AVAILABLE").createdAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").servicePeriod("2022-02-02T02:03:02~2022-02-02T02:03:02").build();
        ServiceRunningContext serviceRunningContext2 = ServiceRunningContext.builder().serviceName("독서모임3").impossibleTerminatedAt("2022-02-02T02:03:02").state("AVAILABLE").createdAt("2022-02-02T02:03:02").expiredAt("2022-02-02T02:03:02").terminatedAt("2022-02-02T02:03:02").servicePeriod("2022-02-02T02:03:02~2022-02-02T02:03:02").build();

        MembershipInfo membershipInfo = MembershipInfo.builder().id("membershipId").name("클럽-냥냥").build();

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
        return users.stream().filter(u -> u.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public List<User> searchUserByPhoneNumber(String phoneNumber) {
        return users.stream().filter(u -> u.getPhoneNumber().equals(phoneNumber)).collect(Collectors.toList());
    }

    @Override
    public List<User> searchUserByUserName(String userName) {
        return users.stream().filter(u -> u.getUserName().equals(userName)).collect(Collectors.toList());
    }
}
