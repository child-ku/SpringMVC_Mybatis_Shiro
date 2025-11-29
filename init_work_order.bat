@echo off
mysql -u root -proot smm_05_01 < init\sql\work_order.sql
echo Work order tables initialization completed successfully!
pause