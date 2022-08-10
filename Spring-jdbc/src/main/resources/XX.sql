create table "XXX"
(
    id                               varchar(255)             not null
        constraint "XXX_pkey"
            primary key,
    "userID"                         varchar(255)             not null,
    "clubID"                         varchar(255)             not null,
    "clubApplicationID"              varchar(255),
    role                             varchar(255)             not null,
    "refundStatus"                   varchar(255),
    "createdAt"                      timestamp with time zone not null,
    "updatedAt"                      timestamp with time zone not null,
    "deletedAt"                      timestamp with time zone
);

INSERT INTO "XXX" VALUES ('2','2204','1','390f6a1','a948290f','Member',null,null,'2022-03-13 12:02:13.084000 +00:00','2022-04-08 10:21:35.282000 +00:00',null);
