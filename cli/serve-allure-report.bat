SET allure_command=C:\Users\anton\IdeaProjects\ui-hobby-project\.allure\allure-2.24.0\bin\allure
SET results_dir=C:\Users\anton\IdeaProjects\ui-hobby-project\target\allure-results
SET report_dir=C:\Users\anton\IdeaProjects\ui-hobby-project\target\allure-report-data

rem generate allure report
call %allure_command% generate %results_dir% --clean -o %report_dir%

rem open allure report
call %allure_command% open %report_dir%
