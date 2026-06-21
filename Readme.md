DB users and Passwords
Oracle SYSTEM Oracle123
APP_USER Password123

create a oracle db:

docker run -d -p 1521:1521 -p 5500:5500 -e ORACLE_PWD=Oracle123 --name oracle-db container-registry.oracle.com/database/free:latest

##create a vault container:

docker run --cap-add=IPC_LOCK -d --name=vault -p 8200:8200 -e VAULT_DEV_ROOT_TOKEN_ID=myroot -e VAULT_DEV_LISTEN_ADDRESS=0.0.0.0:8200 hashicorp/vault