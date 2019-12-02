#Example

mvn spring-boot:run

mvn spring-boot:run -Drun.jvmArguments="-XX:+UseSerialGC -Xss512k -XX:MaxRAM=256m -Xms256m Xmx512m"

mvn spring-boot:run -Drun.jvmArguments="-XX:+UseSerialGC -Xms256M -Xmx256M -XX:PermSize=128m -XX:MaxPermSize=128m"

curl -i -X POST -H "Content-Type: application/json" -d '{ "list": ["}{","{}{}","{{{}"] }' http://localhost:8080/analyzes

curl -i -X POST -H "Content-Type: application/json" -d '{ "list": ["","{{}}","{}{{}}"] }' http://localhost:8080/analyzes

