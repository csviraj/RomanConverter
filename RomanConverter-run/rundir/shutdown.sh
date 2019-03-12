#!/bin/sh

APP_NAME="RomanConverter"
PID=$(ps -ef | grep -i $APP_NAME | grep -v grep | awk '{print $2}')

if [ -z $PID ];
then
  logger -s "Can't find a matching process!  pattern='$PROCESS_PATTERN'"
  exit;
else
  echo "Shutdown initiated for $APP_NAME (pid=$PID)"
  logger -t $APP_NAME "Shutdown Initiated (pid=$PID)"
  kill $PID
fi

