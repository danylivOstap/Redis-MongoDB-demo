# Redis-MongoDB-demo

Simple Redis MongoDB app.

To start Redis and MongoDb instances run:

```bash
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:latest
