@echo off
mysql -u root -proot -e "CREATE DATABASE IF NOT EXISTS smm_05_01;"
mysql -u root -proot smm_05_01 < init\sql\init_shiro.sql
echo Database initialization completed successfully!
pause