# kafkastreamsdemo please follow the steps to run the program 

 Step-1 : 
 cd /src/kafkastreamdemo

 Step-2 #get the IP address of your machine and update the IP in the docker-compose.yaml file 
 #*KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://<your ID address>:9092,
 #(following command is for mac terminal)
 
 ifconfig | grep "inet " | grep -Fv 127.0.0.1 | awk '{print $2}'

 Step-3
 docker compose up

 Step-3 Make sure Kafka broker is up and running

   docker compose ps


 Step-4 in another terminal screen go to the same folder to create a topic
   cd /src/kafkastreamdemo
   docker exec -it gpkafkabroker kafka-topics.sh --create --topic test-topic --bootstrap-server <ipaddress>:9092 --replication-factor 1 --partitions 4

 Step-5 : Once topic created verify
    docker exec -it gpkafkabroker kafka-topics.sh --bootstrap-server <ipaddress>:9092 --list


Step-6 : Run this main program and make sure it's registered with kafka-broker , it will print a log as follows in the kafka server log.
    Assignment received from leader gpdev1-7b8296aa-ff88-4872-ac32-c2d43e6797f3-StreamThread-1-consumer-69d66da9-7f4e-452e-82ac-d10693072f6d for group gpdev1 for generation 5. The group has 1 members, 0 of which are static.

Step-7: publish some message
    docker exec -it gpkafkabroker kafka-console-producer.sh --topic test-topic --bootstrap-server <ipaddress>:9092
 
