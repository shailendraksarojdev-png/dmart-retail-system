create table brand (
    id BIGSERIAL primary key,
    name varchar(255) not null unique,
    description text null,
    active boolean default true not null,
    created_at timestamp default current_timestamp not null,
    updated_at timestamp default current_timestamp not null
);

