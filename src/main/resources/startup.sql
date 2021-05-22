create database if not exists recipe_dev;
create database if not exists recipe_prod;

# create different user accounts
create user if not exists 'recipe_dev_user'@'localhost' identified by 'anvesh';
create user if not exists 'recipe_prod_user'@'localhost' identified by 'anvesh';

# database grant
grant select on recipe_dev.* to 'recipe_dev_user'@'localhost';
grant insert on recipe_dev.* to 'recipe_dev_user'@'localhost';
grant delete on recipe_dev.* to 'recipe_dev_user'@'localhost';
grant update on recipe_dev.* to 'recipe_dev_user'@'localhost';
grant select on recipe_prod.* to 'recipe_prod_user'@'localhost';
grant insert on recipe_prod.* to 'recipe_prod_user'@'localhost';
grant delete on recipe_prod.* to 'recipe_prod_user'@'localhost';
grant update on recipe_prod.* to 'recipe_prod_user'@'localhost';

