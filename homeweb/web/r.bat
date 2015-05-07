call mvn clean package
rem del /S /Q "E:\Work\server\jboss-eap-6.1\standalone\deployments\tmp"
del /S /Q "E:\Work\server\jboss-eap-6.1\standalone\deployments"
copy target\homeweb.war E:\Work\server\jboss-eap-6.1\standalone\deployments