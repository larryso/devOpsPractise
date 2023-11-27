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
docker run -ti -rm ubuntu /bin/bash
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
docker run -d -it --name=my-ubuntu -v /app/wwwroot:/usr/share/data ubuntu
`