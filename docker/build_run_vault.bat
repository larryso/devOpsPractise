docker build -t larryso/vault-filesystem:0.1 . -f ./vault/Dockerfile

docker-compose -f ./vault/docker-compose.yml up -d