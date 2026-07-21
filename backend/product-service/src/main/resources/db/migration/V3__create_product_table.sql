create table product (
    id BIGSERIAL primary key,
    name varchar(255) not null unique,
    description text null,
    mrp NUMERIC(10,2) not null,
    selling_price NUMERIC(10,2) not null,
    stock int not null,
    status varchar(20) not null,
    category_id BIGINT not null references catagory(id) on delete cascade,
    brand_id BIGINT not null references brand(id) on delete cascade,
    active boolean default true not null,
    created_at timestamp default current_timestamp not null,
    updated_at timestamp default current_timestamp not null,
    sku varchar(30) not null unique,
    barcode varchar(30) not null unique
);