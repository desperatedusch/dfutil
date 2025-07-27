@echo off
SET JAVA_HOME=D:\tools\jdks\temurin-24.0.1

set "elastic=D:\tools\elk\elasticsearch-9.0.2\bin\elasticsearch.bat"
set "kibana=D:\tools\elk\kibana-9.0.2\bin\kibana.bat"
set "logstash=D:\tools\elk\logstash-9.0.2\bin\logstash.bat"

:kill
tasklist | find /i "cmd.exe" | find "%~1" >nul
if %errorlevel% equ 0 (
    echo %~1 is already running...
    taskkill /f /fi "IMAGENAME eq cmd.exe" /fi "WINDOWTITLE eq %~1"
)
goto :eof

:start
echo Starting %~1...
start "" "%~1"
goto :eof

call :kill "%elastic%"
call :start "%elastic%"

call :kill "%kibana%"
call :star "%kibana%"

call :kill "%logstash%"
call :start "%logstash%"

echo elk processes started....
