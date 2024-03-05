#base image
FROM alpine:3.14

#set vault version
ENV VAULT_VERSION=1.8.2

#create a new directory
RUN mkdir /vault

#download dependencies
RUN apk --no-cache add bash ca-certificates wget curl

#download and setup vault
RUN wget -q -O /tmp/vault.zip https://releases.hashicorp.com/vault/${VAULT_VERSION}/vault_${VAULT_VERSION}_linux_amd64.zip
RUN unzip /tmp/vault.zip -d /vault 
RUN rm -f /tmp/vault.zip
RUN chmod +x /vault

#update PATH
ENV PATH="PATH=$PATH:/vault"

#add config file
COPY ./vault/vault-config.json /vault/config/vault-config.json

#expose port
EXPOSE 8200

#run vault
ENTRYPOINT ["vault"]