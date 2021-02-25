# cookbook
Cookbook(Linux)
Project consist from 3 parts: database, frontend and backend

Database setup: you need to install docker. Then you can run PostgreSQL db with command:
```
sudo docker run --name postgresqldb -e POSTGRES_PASSWORD=postgres -d -p 5432:5432  postgres
```
Backend setup: copy files from this repo and open restapi folder. In file "application.yaml" you can change host and port database and port of backend service. To start backend run this command from /restapi folder:
```
./gradlew run
```
Frontend setup: open /frontend folder and run command(npm should be installed):
```
npm install
```
In file /frontend/src/utils/CookbookAPI.js change host and port of backend
To run frontend in /frontend folder run command:
```
sudo npm start
```

Now you can open the page at localhost:3000 and test the app!
