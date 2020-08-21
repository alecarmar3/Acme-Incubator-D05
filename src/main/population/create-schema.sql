
    create table `accounting_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `status` integer,
        `title` varchar(255),
        `bookkeeper_id` integer not null,
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `activity` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `budget_amount` double precision,
        `budget_currency` varchar(255),
        `end_date` datetime(6),
        `start_date` datetime(6),
        `title` varchar(255),
        `web_links` varchar(255),
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `creation_date` datetime(6),
        `investment_money_offer_amount` double precision,
        `investment_money_offer_currency` varchar(255),
        `statement` varchar(255),
        `status` integer,
        `ticker` varchar(255),
        `investment_round_id` integer not null,
        `investor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `banner` (
       `id` integer not null,
        `version` integer not null,
        `brand` varchar(255),
        `credit_card_number` varchar(255),
        `cvv` varchar(255),
        `expiration_date` varchar(255),
        `holder_name` varchar(255),
        `picture` varchar(255),
        `slogan` varchar(255),
        `target_url` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `bookkeeper` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm_name` varchar(255),
        `responsibility_statement` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `bulletin` (
       `id` integer not null,
        `version` integer not null,
        `author` varchar(255),
        `category` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `average_goal` varchar(255),
        `average_reward_amount` double precision,
        `average_reward_currency` varchar(255),
        `deadline` datetime(6),
        `description` varchar(255),
        `expert_goal` varchar(255),
        `expert_reward_amount` double precision,
        `expert_reward_currency` varchar(255),
        `rookie_goal` varchar(255),
        `rookie_reward_amount` double precision,
        `rookie_reward_currency` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `configuration` (
       `id` integer not null,
        `version` integer not null,
        `activity_sectors` varchar(255),
        `spam_threshold` double precision,
        `spam_words` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `entrepreneur` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `qualification_record` varchar(255),
        `skills_record` varchar(255),
        `start_up_name` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `inquiry` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `email` varchar(255),
        `money_max_amount` double precision,
        `money_max_currency` varchar(255),
        `money_min_amount` double precision,
        `money_min_currency` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investment_round` (
       `id` integer not null,
        `version` integer not null,
        `additional_info` varchar(255),
        `amount_of_money` double precision,
        `creation_date` datetime(6),
        `description` varchar(255),
        `kind_of_round` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        `entrepreneur_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `activity_sector` varchar(255),
        `firm_name` varchar(255),
        `profile` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `investment_round_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `notice` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `header_picture` varchar(255),
        `title` varchar(255),
        `web_links` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `overture` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `email` varchar(255),
        `money_max_amount` double precision,
        `money_max_currency` varchar(255),
        `money_min_amount` double precision,
        `money_min_currency` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `inventor_name` varchar(255),
        `source_type` varchar(255),
        `stars` integer,
        `title` varchar(255),
        `update_date` datetime(6),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `research` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `email` varchar(255),
        `money_max_amount` double precision,
        `money_max_currency` varchar(255),
        `money_min_amount` double precision,
        `money_min_currency` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `technology_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `inventor_name` varchar(255),
        `source_type` varchar(255),
        `stars` integer,
        `title` varchar(255),
        `update_date` datetime(6),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool_record` (
       `id` integer not null,
        `version` integer not null,
        `activity_sector` varchar(255),
        `description` varchar(255),
        `email` varchar(255),
        `inventor_name` varchar(255),
        `source_type` varchar(255),
        `stars` integer,
        `title` varchar(255),
        `update_date` datetime(6),
        `website` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDXh8dbpsf17u4nh0t16sw1b86pt on `accounting_record` (`investment_round_id`);
create index IDX2fie4tfqjhatmlwtwev59aqva on `accounting_record` (`bookkeeper_id`);
create index IDX5s5lij699jj18tptn7wrwol2r on `activity` (`end_date`, `investment_round_id`);
create index IDXk5hiuy4v1q26c8edpevbsb6nw on `application` (`status`, `investor_id`);
create index IDXa91bud5njtdgbcs992l3fc8dl on `application` (`investor_id`);

    alter table `application` 
       add constraint UK_ao7wxw7e7mkj6g5q49yq2fw8d unique (`ticker`);

    alter table `banner` 
       add constraint UK_p8qqa0apsb9hsacl8y4mie6m unique (`credit_card_number`);
create index IDXnr284tes3x8hnd3h716tmb3fr on `challenge` (`deadline`);
create index IDX9u3lu85o98y0tro95qasghg8e on `inquiry` (`deadline`);
create index IDXcaskyi2xiccqj4na2coao5m4o on `investment_round` (`entrepreneur_id`);

    alter table `investment_round` 
       add constraint UK_408l1ohatdkkut5bkt0eu6ifs unique (`ticker`);
create index IDXbeg6sje8j6vxxen08e9tv15br on `message` (`investment_round_id`);
create index IDX3ianip0mmnj1316lpeas2yw71 on `overture` (`deadline`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `accounting_record` 
       add constraint `FK41jm4vk7runvmg5tderffrele` 
       foreign key (`bookkeeper_id`) 
       references `bookkeeper` (`id`);

    alter table `accounting_record` 
       add constraint `FKk1pmfnppwk0kav7xloy8u71uq` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `activity` 
       add constraint `FK1ufotopeofii4jlefyk9c7os5` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FKk5ibe41quxsif8im882xv4afo` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `application` 
       add constraint `FKl4fp0cd8c008ma79n6w58xhk9` 
       foreign key (`investor_id`) 
       references `investor` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `bookkeeper` 
       add constraint FK_krvjp9eaqyapewl2igugbo9o8 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `entrepreneur` 
       add constraint FK_r6tqltqvrlh1cyy8rsj5pev1q 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `investment_round` 
       add constraint `FKkj1l8c2ftn9c65y061me6t37j` 
       foreign key (`entrepreneur_id`) 
       references `entrepreneur` (`id`);

    alter table `investor` 
       add constraint FK_dcek5rr514s3rww0yy57vvnpq 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `message` 
       add constraint `FKc4w1gj5ff1rob521gutst6l2r` 
       foreign key (`investment_round_id`) 
       references `investment_round` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
