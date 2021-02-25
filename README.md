# cookbook
Cookbook
Project consist from 3 parts: database, frontend and backend

To setup database you need to install docker. Then you can run PostgreSQL db with command:
sudo docker run --name postgresqldb -e POSTGRES_PASSWORD=postgres -d -p 5432:5432  postgres
