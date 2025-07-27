
@echo off

set "elastic=D:\tools\elk\elasticsearch-9.0.2\bin\elasticsearch.bat"
set "kibana=D:\tools\elk\kibana-9.0.2\bin\kibana.bat"
set "logstash=D:\tools\elk\logstash-9.0.2\bin\logstash.bat"

:kill_process
tasklist | find /i "cmd.exe" | find "%~1" >nul
if %errorlevel% equ 0 (
    echo Process %~1 will be shutdown...
    taskkill /f /im "%~1"
)
goto :eof

call :kill_process "%elastic%"
call :kill_process "%kibana%"
call :kill_process "%logstash%"

echo elk processes stopped....