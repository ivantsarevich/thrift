create table transactions
(
    id           bigint primary key generated by default as identity,
    account_from bigint                   not null,
    account_to   bigint                   not null,
    currency     varchar                  not null,
    amount       numeric(32, 2)           not null,
    category     varchar                  not null,
    date         timestamp with time zone not null
);