#!/bin/sh
if [ "$1" = "build" ];then
    mkdir /home/changsheng/project/project60999/project
    cp -a /home/changsheng/project/project60999/server/. /home/changsheng/project/project60999/project/
    cd /home/changsheng/project/project60999/project
    rm -rf /home/changsheng/project/project60999/server
    echo "执行成功"
fi
