# run ubuntu in docker

## 1.  getting ubuntu docker image
`
docker pull ubuntu
`

inspect images

`
docker image -a
`

## 2. Running the ubuntu image

`
docker run -ti ubuntu /bin/bash
`
the command above tells docker to run the docker ubuntu container in an interactive terminal mode (-ti)
the /bin/bash argument is a way to tell the container to run the bash 
the -rm flag instructs docker to automatically remove the docker container after we stop it

login container:

`
docker exec -it 'container id' /bin/bash
`

## 3.  install software

`
apt-get update

apt-get install jq
`

## 4. delete a container

`
docker stop container_id
docker rm container_id
`

## 5. run container and mount a volum

`
docker run -d -it --name=my-ubuntu -v [your local path]:/usr/share/data ubuntu

docker run -d -it --name=my-ubuntu -v ubuntu-local:/home ubuntu

## 6. Docker run MYSQL

```
docker pull mysql

docker run -p 3306:3306 --name mysql -v F:\temp\docker-mysql\log:/var/log/mysql -v F:\temp\docker-mysql\mysql:/var/lib/mysql -v F:\temp\docker-mysql\conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=root -d mysql:latest

```

Please note: you have to run the mysql without mounting and then copy /etc/my.conf to F:\temp\docker-mysql\conf, then run command above

after container rrunning, then 

```
docker exec -it mysql-server mysql -u root -p
>> MYSQL  alter user 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root'
          flush privileges
```
