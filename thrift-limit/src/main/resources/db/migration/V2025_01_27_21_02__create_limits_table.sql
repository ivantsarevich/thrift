create table limits
(
    id             bigint primary key generated by default as identity,
    account_id     bigint                   not null,
    sum_limit      numeric(32, 2)           not null,
    category       varchar(32)              not null,
    limit_currency varchar                  not null,
    limit_datetime timestamp with time zone not null
);