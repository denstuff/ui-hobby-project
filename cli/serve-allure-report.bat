SET allure_command=.allure\allure-2.24.0\bin\allure
SET results_dir=target\allure-results
SET report_dir=target\allure-report-data

rem go to root project dir
cd ..\

rem generate allure report
call %allure_command% generate %results_dir% --clean -o %report_dir%

rem open allure report
call %allure_command% open %report_dir%
