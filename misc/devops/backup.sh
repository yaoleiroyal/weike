#!/bin/sh
time=`date "+%Y%m%d%H%M%S"`
back_dir=~/h2double_backup
tomcat_dir=~/tomcat/
cd $back_dir
cp -R $tomcat_dir/webapps/h2double/attachment/ attachment-$time/
mysqldump -uroot -paetc@jk -B h2double > h2double-$time.sql
