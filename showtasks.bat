call runcrud
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo RUNCRUD could not start.
goto fail

:openbrowser
start http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo Errors occured.

:end
echo.
echo Job is finished.