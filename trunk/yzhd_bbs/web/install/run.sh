#!/bin/bash
FTP_HOME=/home/cont
TOMCAT_HOME=/usr/local/jakarta-tomcat-5.0.28
APACHE_HOME=/usr/local/apache2

chmod 777 $FTP_HOME/log/*

cd $CONTENTS_HOME

start(){
    ${CONTENTS_HOME}/bin/contentsstart.sh start
    exitcode=$?
    if [ $exitcode -eq 255 ] ; then
       exit $exitcode
    fi
    sleep 6 
    ${APACHE_HOME}/bin/apachectl start
    sleep 1
    echo "tomcat start"
    ${TOMCAT_HOME}/bin/catalina.sh run
}
stop(){
    ${TOMCAT_HOME}/bin/shutdown.sh
    sleep 1
    ${CONTENTS_HOME}/bin/contentsstart.sh stop
}
 
case $1  in
  start)
      start
  ;;
  stop)
      stop
  ;;
  restart)
      stop
      sleep 2
      start
  ;;

  *)
   echo "Usage $1 (start|stop|restart)"
  ;;

esac

