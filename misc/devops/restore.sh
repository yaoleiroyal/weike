#!/bin/sh

back_dir=~/h2double_backup
tomcat_dir=~/tomcat/

cd $back_dir
last_back_attachment=`ls $back_dir/attachment* -d | tail -n 1`
cp -R $last_back_attachment $tomcat_dir/webapps/h2double/attachment
