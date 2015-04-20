cd ../parent_pom
call mvn clean install
cd ../db
call mvn clean install
cd ../web
call run.bat