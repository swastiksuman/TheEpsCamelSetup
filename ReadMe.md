## Oracle Setup

### Docker Image 
docker run -d -p 49161:1521 -p 8080:8080 oracleinanutshell/oracle-xe-11g

hostname: localhost
port: 49161
sid: xe
username: system
password: oracle

http://localhost:8080/apex/apex_admin

admin/admin

### DDL


### DML 


## IBM MQ Setup

### Docker Image
docker run   --env LICENSE=accept   --env MQ_QMGR_NAME=QM1   --publish 1414:1414   --publish 9443:9443   --detach   ibmcom/mq


### Creating Local Queue
runmqsc QM1
define qlocal(LQ1)   
end

