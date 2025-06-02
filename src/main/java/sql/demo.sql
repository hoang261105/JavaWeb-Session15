create database ss15;
use ss15;

create table users
(
    id       int primary key auto_increment,
    name     varchar(255) not null,
    email    varchar(255) not null unique,
    password varchar(100) not null
);

create table products
(
    product_id   int primary key auto_increment,
    product_name varchar(255) not null,
    price        decimal(15, 2),
    description  text
);

create table reviews(
    id int primary key auto_increment,
    product_id int,
    user_id int,
    foreign key (product_id) references products(product_id),
    foreign key (user_id) references users(id),
    rating int not null,
    comment text
);

create table carts(
    cart_id int primary key auto_increment,
    user_id int,
    product_id int,
    foreign key (user_id) references users(id),
    foreign key (product_id) references products(product_id),
    quantity int default 1
);

create table resumes(
    id int primary key auto_increment,
    full_name varchar(255) not null,
    email varchar(255) not null unique,
    phone_number varchar(10) not null unique,
    education varchar(200) not null,
    experience varchar(200) not null,
    skills varchar(100) not null,
    image text
);

insert into users(name, email, password)
values ('Hoàng', 'hoang@gmail.com', 'hoang123'),
       ('Phương Anh', 'phanh@gmail.com', 'phanh123');

INSERT INTO products (product_name, price, description)
VALUES ('Laptop Dell XPS 13', 29999000.00, 'Laptop mỏng nhẹ, màn hình 13 inch, chip Intel Core i7'),
       ('Điện thoại iPhone 14 Pro', 25999000.00, 'Smartphone cao cấp của Apple, camera sắc nét'),
       ('Tai nghe AirPods Pro', 4990000.00, 'Tai nghe không dây, chống ồn chủ động'),
       ('Màn hình LG 27UL850', 8990000.00, 'Màn hình 27 inch 4K UHD, chuẩn màu cao'),
       ('Chuột Logitech MX Master 3', 2490000.00, 'Chuột không dây đa chức năng cho dân văn phòng');


DELIMITER \\
create procedure check_login(
    email_in varchar(255),
    password_in varchar(100)
)
begin
    select *
    from users
    where email = email_in
      and password = password_in;
end;

create procedure find_by_email(
    email_in varchar(255)
)
begin
    select *
    from users
    where email = email_in;
end;

create procedure find_user_by_id(
    id_in int
)
begin
    select u.name
    from users u
    join reviews r on u.id = r.user_id
    where u.id = id_in;
end;
DELIMITER \\

DELIMITER \\
create procedure get_all_products()
begin
    select * from products;
end;

create procedure get_product_by_id(
    id_in int
)
begin
    select * from products
    where product_id = id_in;
end;

create procedure save_review(
    product_id_in int,
    user_id_in int,
    rating_in int,
    comment_in text
)
begin
    insert into reviews(product_id, user_id, rating, comment)
    values (product_id_in, user_id_in, rating_in, comment_in);
end;

create procedure get_reviews_by_id(
    product_id_in int
)
begin
    select * from reviews
    where product_id = product_id_in;
end;
DELIMITER \\

DELIMITER \\
create procedure add_to_cart(
    user_id_in int,
    product_id_in int
)
begin
    insert into carts(user_id, product_id)
    values (user_id_in, product_id_in);
end;

create procedure update_cart(
    user_id_in int,
    product_id_in int
)
begin
    update carts
    set quantity = quantity + 1
    where product_id = product_id_in and user_id = user_id_in;
end;

create procedure get_all_carts(
    user_id_in int
)
begin
    select * from carts
    where user_id = user_id_in;
end;

create procedure get_name_by_id(
    product_id_in int
)
begin
    select p.product_name
    from products p
    join carts c on p.product_id = c.product_id
    where p.product_id = product_id_in;
end;

create procedure get_price_by_id(
    product_id_in int
)
begin
    select p.price
    from products p
    join carts c on p.product_id = c.product_id
    where p.product_id = product_id_in;
end;
DELIMITER \\

-- Hiển thị
DELIMITER \\
create procedure get_all_resumes()
begin
    select * from resumes;
end;

create procedure add_resume(
    full_name_in varchar(255),
    email_in varchar(255),
    phone_number_in varchar(10),
    education_in varchar(200),
    experience_in varchar(200),
    skills_in varchar(100),
    image_in text
)
begin
    insert into resumes(full_name, email, phone_number, education, experience, skills, image)
    values (full_name_in, email_in, phone_number_in, education_in, experience_in, skills_in, image_in);
end;

create procedure check_exist_email(
    email_in varchar(200),
    id_in int
)
begin
    select count(*) as is_exist_name
    from resumes
    where email = email_in
    and (id_in is null or id != id_in);
end;

create procedure check_exist_phone(
    phone_in varchar(200),
    id_in int
)
begin
    select count(*) > 0 as is_exist_phone
    from resumes
    where phone_number = phone_in
    and (id_in is null or id != id_in);
end;

create procedure find_resume_by_id(
    id_in int
)
begin
    select * from resumes
    where id = id_in;
end;

create procedure update_resume(
    id_in int,
    full_name_in varchar(255),
    email_in varchar(255),
    phone_number_in varchar(10),
    education_in varchar(200),
    experience_in varchar(200),
    skills_in varchar(100),
    image_in text
)
begin
    update resumes
    set full_name = full_name_in,
        email = email_in,
        phone_number = phone_number_in,
        education = education_in,
        experience = experience_in,
        skills = skills_in,
        image = image_in
    where id = id_in;
end;

create procedure delete_resume(
    id_in int
)
begin
    delete from resumes
    where id = id_in;
end;
DELIMITER \\